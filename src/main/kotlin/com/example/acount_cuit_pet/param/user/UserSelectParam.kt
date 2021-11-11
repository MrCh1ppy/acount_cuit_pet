package com.example.acount_cuit_pet.param.user

import com.example.acount_cuit_pet.param.base.BasePageAbleParam
import io.swagger.annotations.ApiModel
import javax.validation.constraints.NotNull

@ApiModel
class UserSelectParam constructor(
    @NotNull current: Int,
    @NotNull pageSize: Int,
    val username: String?,
    val nickname: String?
) : BasePageAbleParam(tempCurrent = current, tempPageSize = pageSize) {
}