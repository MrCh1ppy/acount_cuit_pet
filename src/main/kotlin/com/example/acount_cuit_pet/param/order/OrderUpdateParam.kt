package com.example.acount_cuit_pet.param.order

import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class OrderUpdateParam(
    val peopleName: @Size(min = 1) @NotNull(message = "购买人姓名不得为空") String,
    val numOfMoney: @NotNull(message = "金额不得为空") Double,
    val date: @NotNull(message = "日期不得为空") Date,
    val isDonation: @NotNull(message = "是否捐款的信息不得为空") Boolean,
    val title: @Size(min = 1) @NotNull(message = "标题不得为空") String,
    val id: @NotNull(message = "id不得为空") Int
) {
}