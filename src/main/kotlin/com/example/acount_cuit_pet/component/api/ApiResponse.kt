package com.example.acount_cuit_pet.component.api

enum class ApiResponse(val code: Int) {
    SUCCESS(200),
    UNKNOWN_ERROR(501),
    PARAM_ERROR(503),
    INNER_ERROR(504),
    AUTHENTICATION_ERROR(505),
    PROJECT_ERROR(502);
}