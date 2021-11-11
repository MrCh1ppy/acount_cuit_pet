package com.example.acount_cuit_pet.param.base

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiParam
import org.springframework.data.domain.PageRequest
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@ApiModel("基本分页参数")
abstract class BasePageAbleParam constructor(tempPageSize: Int, tempCurrent: Int) {
    @ApiParam(value = "页大小", defaultValue = "10")
    @get:Positive(message = "页大小为大于0的整数")
    @get:NotNull(message = "页大小不得为空")
    val pageSize = tempPageSize

    @ApiParam(value = "当前页", defaultValue = "1")
    @get:NotNull(message = "页数不得为空")
    @get:Positive(message = "页数为大于0的整数")
    val current = tempCurrent

    fun toPageRequest(): PageRequest {
        return PageRequest.of(current - 1, pageSize)
    }
}