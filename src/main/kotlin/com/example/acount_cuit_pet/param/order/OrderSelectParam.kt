package com.example.acount_cuit_pet.param.order

import com.example.acount_cuit_pet.param.base.BasePageAbleParam
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.Value

@EqualsAndHashCode(callSuper = true)
@Value
class OrderSelectParam @Builder constructor(
    pageSize: Int,
    current:  Int,
    var peopleName: String?,
    var isDonation: Boolean?,
    var month: Int?, var year: Int?,
    var maxMoney: Double?, var minMoney: Double?, var title: String?
) : BasePageAbleParam(pageSize, current)