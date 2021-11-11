package com.example.acount_cuit_pet.component.exception

import com.example.acount_cuit_pet.component.api.ApiResponse

//直接调用构造函数
data class ProjectException constructor(val msg: String, val apiResponse: ApiResponse = ApiResponse.PROJECT_ERROR) :
    Exception(msg) {
}