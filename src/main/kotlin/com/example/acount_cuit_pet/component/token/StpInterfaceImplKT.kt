package com.example.acount_cuit_pet.component.token

import cn.dev33.satoken.stp.StpInterface
import com.example.acount_cuit_pet.component.api.ApiResponse
import com.example.acount_cuit_pet.component.exception.ProjectException

class StpInterfaceImplKT:StpInterface {
    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    override fun getPermissionList(loginId: Any?, loginType: String?): List<String?>? {
        return null
    }

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    override fun getRoleList(loginId: Any?, loginType: String?): List<String?> {
        val temp = loginId ?: throw ProjectException("loginID为空", ApiResponse.INNER_ERROR)
        val id = temp as LoginId
        val res = ArrayList<String?>()
        res.add(id.identity)
        return res
    }
}