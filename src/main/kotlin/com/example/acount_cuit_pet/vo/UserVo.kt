package com.example.acount_cuit_pet.vo

import io.swagger.annotations.ApiModel

@ApiModel
data class UserVo constructor(
    val username: String,
    val nickname: String,
    val identity: String
) {}
