package com.example.acount_cuit_pet

import com.example.acount_cuit_pet.dao.UserDao
import com.example.acount_cuit_pet.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AcountCuitPetApplicationTests {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userDao: UserDao




    @Test
    fun userDaotest() {
        val user = userDao.getByUsername("admin")
        println(user)
    }
}
