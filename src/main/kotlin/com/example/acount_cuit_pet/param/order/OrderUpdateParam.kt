package com.example.acount_cuit_pet.param.order

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

data class OrderUpdateParam(
    @get:NotBlank(message = "购买人姓名不得为空")
    val peopleName:String,
    @get:NotNull(message = "金额不得为空")
    val numOfMoney:Double,
    @get:NotNull(message = "日期不得为空")
    @get:PastOrPresent(message = "需要为过去或现在的时间")
    val date:Date,
    @get:NotBlank(message = "是否捐款的信息不得为空")
    val isDonation:Boolean,
    @get:NotBlank(message = "标题不得为空")
    val title: String,
    @get:NotNull(message = "id不得为空")
    val id:Int
) {
}