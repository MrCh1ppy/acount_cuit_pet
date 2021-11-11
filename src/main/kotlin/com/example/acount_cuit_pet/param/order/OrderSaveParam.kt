package com.example.acount_cuit_pet.param.order

import lombok.EqualsAndHashCode
import lombok.Value
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

@EqualsAndHashCode(callSuper = true)
@Value
data class OrderSaveParam constructor(
    @get:NotBlank(message = "购买人不得为空")
    val peopleName: String? = null,
    @get:NotNull(message = "金额不得为空")
    val numOfMoney:  Double? = null,
    @get:NotNull(message = "日期不得为空")
    @get:PastOrPresent
    val date: Date? = null,
    @get:NotNull(message = "是否为捐款的信息不得为空")
    val isDonation: Boolean? = null,
    @get:NotBlank(message = "标题不得为空")
    val title: String? = null
) {}
