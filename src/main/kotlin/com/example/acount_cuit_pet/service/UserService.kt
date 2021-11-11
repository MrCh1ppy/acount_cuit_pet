package com.example.acount_cuit_pet.service

import com.example.acount_cuit_pet.entity.ProjectUser
import com.example.acount_cuit_pet.param.user.UserLoginParam
import com.example.acount_cuit_pet.param.user.UserSelectParam
import com.example.acount_cuit_pet.vo.LoginVo
import com.example.acount_cuit_pet.vo.UserVo
import org.springframework.data.domain.Page

interface UserService {
    fun save(user: ProjectUser): ProjectUser?
    fun drop(id: Int)
    fun select(userSelectParam: UserSelectParam): Page<UserVo>
    fun login(userLoginParam: UserLoginParam): LoginVo
}