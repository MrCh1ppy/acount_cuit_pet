package com.example.acount_cuit_pet.param.user

import io.swagger.annotations.ApiModel
import javax.validation.constraints.NotNull

@ApiModel
data class UserSaveParam constructor(
    @NotNull(message = "用户名不得为空") val username: String?,
    @NotNull(message = "密码不得为空") val password: String?,
    @NotNull(message = "身份不得为空") val identity: String?,
    @NotNull(message = "昵称不得为空") val nickname: String?
) {
}