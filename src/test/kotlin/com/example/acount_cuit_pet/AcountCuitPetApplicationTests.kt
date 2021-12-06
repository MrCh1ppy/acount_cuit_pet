package com.example.acount_cuit_pet

import com.example.acount_cuit_pet.controller.UserController
import com.example.acount_cuit_pet.dao.UserDao
import com.example.acount_cuit_pet.param.user.UserSelectParam
import com.example.acount_cuit_pet.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AcountCuitPetApplicationTests {

    @Autowired
    lateinit var userDao: UserDao

    @Autowired
    lateinit var userController: UserController



    @Test
    fun userDaotest() {
        val user = userDao.getByUsername("admin")
        println(user)
    }

    @Test
    fun userControllerTest(){
        val param = UserSelectParam(
            current = 1,
            pageSize = 10
        )
        val apiResult = userController.select(param)
        println(apiResult.data?.content?.get(0)?.identity)
    }


}
