package com.example.acount_cuit_pet.param.user

import io.swagger.annotations.ApiModel
import javax.validation.constraints.NotNull

@ApiModel
data class UserLoginParam(
    @NotNull(message = "用户名不得为空") val username: String?,
    @NotNull(message = "密码不得为空") val password: String?
) {}