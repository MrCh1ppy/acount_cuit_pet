package com.example.acount_cuit_pet.param.user

import io.swagger.annotations.ApiModel
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel
data class UserLoginParam(
    @get:NotNull(message = "用户名不得为空") @get:Size(min = 1,message = "用户名不得为空") val username: String?,
    @get:NotNull(message = "密码不得为空") @get:Size(min=1,message = "密码不得为空") val password: String?
) {}