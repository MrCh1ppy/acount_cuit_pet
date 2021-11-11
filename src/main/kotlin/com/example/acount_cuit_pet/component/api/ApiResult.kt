package com.example.acount_cuit_pet.component.api

class ApiResult<T> private constructor(
    val apiCode: Int,
    val data: T?,
    val msg: String
) {
    companion object {
        fun <T> get(apiCode: ApiResponse, data: T?, msg: String): ApiResult<T> {
            return ApiResult(apiCode.ordinal, data, msg)
        }

        fun <T> ok(data: T?, msg: String = "操作成功"): ApiResult<T> {
            return get(ApiResponse.SUCCESS, data, msg)
        }

        fun error(errorPos: String, errorCause: String, apiCode: ApiResponse): ApiResult<String> {
            return get(apiCode, errorPos, errorCause)
        }
    }
}