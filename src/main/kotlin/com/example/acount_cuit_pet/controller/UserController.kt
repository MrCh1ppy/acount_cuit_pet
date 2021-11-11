package com.example.acount_cuit_pet.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.annotation.SaCheckRole
import cn.dev33.satoken.stp.StpUtil
import com.example.acount_cuit_pet.component.api.ApiResult
import com.example.acount_cuit_pet.entity.ProjectUser
import com.example.acount_cuit_pet.param.user.UserLoginParam
import com.example.acount_cuit_pet.param.user.UserSaveParam
import com.example.acount_cuit_pet.param.user.UserSelectParam
import com.example.acount_cuit_pet.response.user.UserLoginResponse.*
import com.example.acount_cuit_pet.service.UserService
import com.example.acount_cuit_pet.vo.UserVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping("/save")
    @SaCheckRole("admin")
    fun save(@NotNull @Validated param: UserSaveParam): ApiResult<ProjectUser> {
        val (username, password, identity, nickname) = param
        val projectUser = ProjectUser().apply {
            this.nickname = nickname
            this.username = username
            this.passwordMd5 = password
            this.identity = identity
        }
        val save = userService.save(projectUser)
        return ApiResult.ok(save)
    }

    @PostMapping("/query")
    @SaCheckLogin
    fun select(@NotNull @Validated userSelectParam: UserSelectParam): ApiResult<Page<UserVo>> {
        val page = userService.select(userSelectParam)
        return ApiResult.ok(page)
    }

    @GetMapping("/delete")
    @SaCheckRole("admin")
    fun drop(@NotNull id: Int): ApiResult<String> {
        userService.drop(id = id)
        return ApiResult.ok("删除成功")
    }

    @PostMapping("/login")
    fun login(@NotNull @Validated param: UserLoginParam): ApiResult<String> {
        return when (userService.login(param)) {
            ERROR_PASSWORD -> ApiResult.ok(null, "密码错误")
            ERROR_USERNAME -> ApiResult.ok(null, "用户名错误")
            ERROR_PARAM -> ApiResult.ok(null, "参数错误")
            SUCCESS -> ApiResult.ok(StpUtil.getTokenValue(), "成功")
        }
    }
}