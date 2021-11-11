package com.example.acount_cuit_pet.service.impl

import cn.dev33.satoken.secure.SaSecureUtil
import cn.dev33.satoken.stp.StpUtil
import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.component.token.LoginId
import com.example.acount_cuit_pet.dao.UserDao
import com.example.acount_cuit_pet.entity.ProjectUser
import com.example.acount_cuit_pet.param.user.UserLoginParam
import com.example.acount_cuit_pet.param.user.UserSelectParam
import com.example.acount_cuit_pet.response.user.UserLoginResponse
import com.example.acount_cuit_pet.response.user.UserLoginResponse.*
import com.example.acount_cuit_pet.service.UserService
import com.example.acount_cuit_pet.vo.LoginVo
import com.example.acount_cuit_pet.vo.UserVo
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
@Slf4j
class UserServiceImpl : UserService {
    @Autowired
    lateinit var userDao: UserDao
    override fun save(user: ProjectUser): ProjectUser? {
        if (ifUserExist(user)) {
            throw ProjectException("用户名已存在")
        }
        user.passwordMd5 = SaSecureUtil.md5(user.passwordMd5)
        return userDao.save(user)
    }

    private fun ifUserExist(user: ProjectUser): Boolean {
        val target: String = if (user.username == null) "" else user.username!!
        return userDao.findByUsername(target) != null
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
        return when (loginPrivate(userLoginParam)) {
            ERROR_USERNAME -> LoginVo(msg = "用户名错误")
            ERROR_PASSWORD -> LoginVo(msg = "密码错误")
            ERROR_PARAM -> LoginVo(msg = "参数丢失")
            SUCCESS ->{
                var res="normal"
                val loginId = StpUtil.getLoginId()
                if (loginId is LoginId) {
                    res=loginId.identity?:"normal"
                }
                LoginVo(
                    tokenValue = StpUtil.getTokenValue(),
                    tokenName = StpUtil.getTokenName(),
                    identity = res
                )
            }
        }
    }

    private fun privateSelect(userSelectParam: UserSelectParam): Page<ProjectUser> {
        val projectUser = ProjectUser()
            .apply {
                this.username = userSelectParam.username
                this.nickname = userSelectParam.nickname
            }
        /*设置模糊查找*/
        val matcher = ExampleMatcher.matching()
            .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
        val example = Example.of(projectUser, matcher)
        return userDao.findAll(example, userSelectParam.toPageRequest())
    }

    private fun loginPrivate(userLoginParam: UserLoginParam): UserLoginResponse {
        if (userLoginParam.username == null) {
            return ERROR_PARAM
        }
        val user = userDao.findByUsername(userLoginParam.username)?:return ERROR_USERNAME
        if (user.passwordMd5 != SaSecureUtil.md5(userLoginParam.password)) {
            return ERROR_PASSWORD
        }
        StpUtil.login(user.identity)
        return SUCCESS
    }
}