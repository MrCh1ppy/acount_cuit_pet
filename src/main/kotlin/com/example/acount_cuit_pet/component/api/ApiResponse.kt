package com.example.acount_cuit_pet.component.api

enum class ApiResponse(code: Int) {
    SUCCESS(200),
    UNKNOWN_ERROR(501),
    PARAM_ERROR(503),
    INNER_ERROR(504),
    PROJECT_ERROR(502);
}