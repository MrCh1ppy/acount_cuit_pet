package com.example.acount_cuit_pet.dao

import com.example.acount_cuit_pet.entity.ProjectUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserDao : JpaRepository<ProjectUser, Int>, JpaSpecificationExecutor<ProjectUser> {
    fun findByUsername(username: String): ProjectUser?
}