package com.example.acount_cuit_pet.vo

import java.time.LocalDate

data class OrderVo constructor(
    val money: Double?,
    val title: String?,
    val peopleName: String?,
    val date: LocalDate?,
    val isDonation: Boolean?,
    val id:Int?
) {
}