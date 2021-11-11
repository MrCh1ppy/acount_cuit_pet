package com.example.acount_cuit_pet.param.base

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiParam
import javax.validation.constraints.NotNull

@ApiModel
data class BaseUpdateParam(
    @ApiParam(required = true)
    @NotNull
    val id: Int
) {
}