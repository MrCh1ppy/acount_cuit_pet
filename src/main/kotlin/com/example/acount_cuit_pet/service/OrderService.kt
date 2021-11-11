package com.example.acount_cuit_pet.service

import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.entity.ProjectOrder
import com.example.acount_cuit_pet.param.order.OrderSaveParam
import com.example.acount_cuit_pet.param.order.OrderSelectParam
import com.example.acount_cuit_pet.vo.OrderPageVo
import org.springframework.data.domain.Page
import java.util.concurrent.ExecutionException

interface OrderService {
    @Throws(ProjectException::class)
    fun save(param: OrderSaveParam): ProjectOrder?

    suspend fun selectPageData(param: OrderSelectParam): Page<ProjectOrder?>?

    suspend fun selectListData(param: OrderSelectParam): List<ProjectOrder?>?

    @Throws(ExecutionException::class, InterruptedException::class)
    fun selectPage(param: OrderSelectParam): OrderPageVo?

    @Throws(ProjectException::class)
    fun dropOrder(id: Int)

    @Throws(ProjectException::class)
    fun findById(id: Int): ProjectOrder?

    @Throws(ProjectException::class)
    fun update(target: ProjectOrder): ProjectOrder?
}