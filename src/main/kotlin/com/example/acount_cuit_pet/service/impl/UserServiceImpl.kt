package com.example.acount_cuit_pet.service.impl

import cn.dev33.satoken.secure.SaSecureUtil
import cn.dev33.satoken.stp.StpUtil
import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.dao.UserDao
import com.example.acount_cuit_pet.entity.ProjectUser
import com.example.acount_cuit_pet.param.user.UserLoginParam
import com.example.acount_cuit_pet.param.user.UserSelectParam
import com.example.acount_cuit_pet.response.user.UserLoginResponse
import com.example.acount_cuit_pet.service.UserService
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
        if (!ifUserExist(user)) {
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

    override fun login(userLoginParam: UserLoginParam): UserLoginResponse {
        if (userLoginParam.username == null) {
            return UserLoginResponse.ERROR_PARAM
        }
        val user = userDao.findByUsername(userLoginParam.username) ?: return UserLoginResponse.ERROR_USERNAME
        if (user.passwordMd5 != SaSecureUtil.md5(userLoginParam.password)) {
            return UserLoginResponse.ERROR_PASSWORD
        }
        StpUtil.login(user.identity)
        return UserLoginResponse.SUCCESS
    }
}