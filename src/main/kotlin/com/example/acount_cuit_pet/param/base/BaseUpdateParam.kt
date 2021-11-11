package com.example.acount_cuit_pet.param.base

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiParam

@ApiModel
data class BaseUpdateParam(
    @ApiParam(required = true)
    val id: Int
) {
}