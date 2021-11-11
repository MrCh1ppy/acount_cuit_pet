package com.example.acount_cuit_pet.controller

import com.example.acount_cuit_pet.component.api.ApiResponse
import com.example.acount_cuit_pet.component.exception.ProjectException
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import java.text.SimpleDateFormat
import java.util.*

sealed interface ControllerHelper {
    fun <T> checkParam(param: T?): T {
        return param ?: throw ProjectException("参数丢失", ApiResponse.PARAM_ERROR)
    }

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        binder.registerCustomEditor(Date::class.java, CustomDateEditor(dateFormat, true))
    }
}