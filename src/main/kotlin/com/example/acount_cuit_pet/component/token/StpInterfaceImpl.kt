package com.example.acount_cuit_pet.component.token

import cn.dev33.satoken.stp.StpInterface
import com.example.acount_cuit_pet.component.api.ApiResponse
import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StpInterfaceImpl : StpInterface {
    @Autowired
    lateinit var userDao: UserDao
    override fun getPermissionList(p0: Any?, p1: String?): MutableList<String> {
        TODO("Not yet implemented")
    }

    override fun getRoleList(p0: Any?, p1: String?): MutableList<String> {
        if (p0 is LoginId) {
            val loginId = p0.identity
            val mutableListOf = mutableListOf<String>()
            loginId?.let {
                mutableListOf.add(loginId)
            }
            return mutableListOf
        }
        throw ProjectException(msg = "loginId非法", ApiResponse.INNER_ERROR)
    }

}