package com.example.acount_cuit_pet.param.user

import com.example.acount_cuit_pet.param.base.BasePageAbleParam
import io.swagger.annotations.ApiModel
import javax.validation.constraints.NotNull

@ApiModel
class UserSelectParam constructor(
    @NotNull current: Int=1,
    @NotNull pageSize: Int=10,
    val username: String?=null,
    val nickname: String?=null,
    val identity:String?=null
) : BasePageAbleParam(tempCurrent = current, tempPageSize = pageSize) {
}