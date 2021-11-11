package com.example.acount_cuit_pet.param.user

import io.swagger.annotations.ApiModel
import javax.validation.constraints.NotBlank

@ApiModel
data class UserSaveParam constructor(
    @get:NotBlank(message = "用户名不得为空") val username: String?,
    @get:NotBlank(message = "密码不得为空") val password: String?,
    @get:NotBlank(message = "身份不得为空") val identity: String?,
    @get:NotBlank(message = "昵称不得为空") val nickname: String?
) {
}