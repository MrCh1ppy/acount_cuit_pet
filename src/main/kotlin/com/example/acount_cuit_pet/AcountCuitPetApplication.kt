package com.example.acount_cuit_pet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
class AcountCuitPetApplication

fun main(args: Array<String>) {
    runApplication<AcountCuitPetApplication>(*args)
}
