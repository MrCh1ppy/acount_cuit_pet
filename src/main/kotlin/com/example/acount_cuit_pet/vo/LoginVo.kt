package com.example.acount_cuit_pet.vo

data class LoginVo constructor(
    val tokenValue: String? = null,
    val tokenName: String? = null,
    val identity: String? = null,
    val msg: String? = "登录成功"
) {
}