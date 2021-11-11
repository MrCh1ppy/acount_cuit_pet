package com.example.acount_cuit_pet.param.order

import lombok.EqualsAndHashCode
import lombok.Value
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@EqualsAndHashCode(callSuper = true)
@Value
data class OrderSaveParam constructor(
    val peopleName: @Size(min = 1) @NotNull(message = "购买人不得为空") String? = null,
    val numOfMoney: @NotNull(message = "金额不得为空") Double? = null,
    val date: @NotNull(message = "日期不得为空") Date? = null,
    val isDonation: @NotNull(message = "是否为捐款的信息不得为空") Boolean? = null,
    val title: @Size(min = 1) @NotNull(message = "标题不得为空") String? = null
) {}
