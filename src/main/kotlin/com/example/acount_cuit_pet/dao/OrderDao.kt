package com.example.acount_cuit_pet.dao

import com.example.acount_cuit_pet.entity.ProjectOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface OrderDao : JpaRepository<ProjectOrder, Int>, JpaSpecificationExecutor<ProjectOrder> {
}