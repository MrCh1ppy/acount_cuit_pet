package com.example.acount_cuit_pet.param.base

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiParam
import org.springframework.data.domain.PageRequest
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull

@ApiModel("基本分页参数")
abstract class BasePageAbleParam constructor(tempPageSize: Int, tempCurrent: Int) {
    @ApiParam(value = "页大小", defaultValue = "10")
    @DecimalMin("0", message = "页大小不得小于0")
    @NotNull(message = "页大小不得为空")
    val pageSize = tempPageSize

    @ApiParam(value = "当前页", defaultValue = "1")
    @DecimalMin("1", message = "页数大于0")
    @NotNull(message = "页数不得为空")
    val current = tempCurrent

    fun toPageRequest(): PageRequest {
        return PageRequest.of(current - 1, pageSize)
    }
}