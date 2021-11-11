package com.example.acount_cuit_pet

import com.example.acount_cuit_pet.dao.UserDao
import com.example.acount_cuit_pet.entity.ProjectUser
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
    fun contextLoads() {
        val apply = ProjectUser().apply {
            this.identity = "admin"
            this.username = "admin"
            this.nickname = "admin"
            this.passwordMd5 = "admin"
        }
        userService.save(apply)
    }


    @Test
    fun userDaotest() {
        val user = userDao.getByUsername("admin")
        println(user)
    }
}
