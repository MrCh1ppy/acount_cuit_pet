package com.example.acount_cuit_pet.service.impl

import cn.dev33.satoken.secure.SaSecureUtil
import cn.dev33.satoken.stp.StpUtil
import com.example.acount_cuit_pet.component.api.ApiResponse
import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.config.GlobalVariablePool
import com.example.acount_cuit_pet.dao.UserDao
import com.example.acount_cuit_pet.entity.ProjectUser
import com.example.acount_cuit_pet.param.user.UserLoginParam
import com.example.acount_cuit_pet.param.user.UserSelectParam
import com.example.acount_cuit_pet.service.UserService
import com.example.acount_cuit_pet.vo.LoginVo
import com.example.acount_cuit_pet.vo.UserVo
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
@Slf4j
class UserServiceImpl : UserService {
    @Autowired
    lateinit var userDao: UserDao
    @Autowired
    lateinit var redisTemplate: RedisTemplate<String,String>

    override fun save(user: ProjectUser): ProjectUser? {
        if (userDao.findByUsername(user.username?:"")!=null) {
            throw ProjectException("用户名已存在")
        }
        user.passwordMd5 = SaSecureUtil.md5(user.passwordMd5)
        return userDao.save(user)
    }

    override fun drop(id: Int) {
        val byId = userDao.findById(id)
        if (byId.isEmpty) {
            throw ProjectException("该用户不存在")
        }
        userDao.delete(byId.get())
    }

    override fun select(userSelectParam: UserSelectParam): Page<UserVo> {
        val page = privateSelect(userSelectParam)
        return page.map {
            it.getVo()
        }
    }

    override fun login(userLoginParam: UserLoginParam): LoginVo {
        val (username, password) = userLoginParam
        val byUsername = userDao.findByUsername(username)?:return LoginVo(null,null,null,"无对应用户名")
        if(SaSecureUtil.md5(password)!=byUsername.passwordMd5){
            return LoginVo(null,null,null,"密码错误")
        }
        val identity=byUsername.identity?:throw ProjectException("异常用户",ApiResponse.AUTHENTICATION_ERROR)
        StpUtil.login(byUsername.username)
        redisTemplate.opsForValue().set(userLoginParam.username,identity,GlobalVariablePool.TOKEN_LAST_TIME,TimeUnit.MINUTES)
        return LoginVo(StpUtil.getTokenValue(),StpUtil.getTokenName(),byUsername.identity,"登录成功")
    }

    override fun logOut() {
        val idAsString = StpUtil.getLoginIdAsString()
        redisTemplate.delete(idAsString)
        StpUtil.logout()
    }

    private fun privateSelect(userSelectParam: UserSelectParam): Page<ProjectUser> {
        val projectUser = ProjectUser()
            .apply {
                this.username = userSelectParam.username
                this.nickname = userSelectParam.nickname
                this.identity=userSelectParam.identity
            }
        /*设置模糊查找*/
        val matcher = ExampleMatcher.matching()
            .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
        val example = Example.of(projectUser, matcher)
        return userDao.findAll(example, userSelectParam.toPageRequest())
    }
}