package com.example.acount_cuit_pet.service.impl

import com.example.acount_cuit_pet.component.api.ApiResponse
import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.dao.OrderDao
import com.example.acount_cuit_pet.entity.ProjectOrder
import com.example.acount_cuit_pet.param.order.OrderSaveParam
import com.example.acount_cuit_pet.param.order.OrderSelectParam
import com.example.acount_cuit_pet.service.OrderService
import com.example.acount_cuit_pet.specfication.order.OrderSelectSpecification
import com.example.acount_cuit_pet.vo.OrderPageVo
import com.example.acount_cuit_pet.vo.PeopleGroupVo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId

@Service
class OrderServiceImpl : OrderService {
    @Autowired
    lateinit var orderDao: OrderDao

    override fun save(param: OrderSaveParam): ProjectOrder? {
        val target = ProjectOrder().apply {
            if (param.date != null) {
                this.date = LocalDate.ofInstant(param.date.toInstant(), ZoneId.systemDefault())
            }
            this.donation = param.isDonation
            this.money = param.numOfMoney
            this.peopleName = param.peopleName
            this.title = param.title
        }
        return orderDao.save(target)
    }

    override suspend fun selectPageData(param: OrderSelectParam): Page<ProjectOrder?>? {
        return orderDao.findAll(OrderSelectSpecification(param), param.toPageRequest())
    }

    override suspend fun selectListData(param: OrderSelectParam): List<ProjectOrder?>? {
        return orderDao.findAll(OrderSelectSpecification(param))
    }

    private suspend fun selectListDataAsync(param: OrderSelectParam): OrderPageVo {
        val list = selectListData(param)
        val targetList: List<ProjectOrder?> = list ?: emptyList<ProjectOrder>()
        val groupBy = targetList.groupBy { it?.peopleName }
        var res = 0.0
        val mutableList = mutableListOf<PeopleGroupVo>()
        for (entry in groupBy) {
            var entryValue = 0.0
            for (order in entry.value) {
                if (order != null) {
                    order.money?.let {
                        if (order.donation == true) {
                            res += it
                        } else {
                            res += -1 * it
                            entryValue += it
                        }
                    }
                    mutableList.add(PeopleGroupVo().apply {
                        this.money = entryValue
                        this.name = order.peopleName
                    })
                }
            }
        }
        return OrderPageVo().apply {
            this.res = res
            this.peopleGroupVos = mutableList
            this.orderPage = null
        }
    }

    private suspend fun selectPageAsync(param: OrderSelectParam): OrderPageVo = coroutineScope {
        val one = async { selectListDataAsync(param) }
        val two = async { selectPageData(param) }
        val orderPageVo = one.await()
        val pageVo = two.await()
        if (pageVo != null) {
            orderPageVo.orderPage = pageVo.map { cur -> cur?.getVo() }
        }
        orderPageVo
    }

    override fun selectPage(param: OrderSelectParam): OrderPageVo? = runBlocking { selectPageAsync(param) }

    override fun dropOrder(id: Int) {
        val byId = orderDao.findById(id)
        if (byId.isEmpty) {
            throw ProjectException(
                msg = "删除时无对应实体",
                apiResponse = ApiResponse.PROJECT_ERROR
            )
        }
        orderDao.delete(byId.get())
    }

    override fun findById(id: Int): ProjectOrder? {
        val byId = orderDao.findById(id)
        return if (byId.isEmpty) null else byId.get()
    }

    override fun update(target: ProjectOrder): ProjectOrder? {
        val id = target.id ?: throw ProjectException("更新参数,id为空", ApiResponse.PROJECT_ERROR)
        val byId = orderDao.findById(id)
        if (byId.isEmpty) {
            throw ProjectException("更新的对应实体不存在", ApiResponse.PROJECT_ERROR)
        }
        val get = byId.get()
        get.apply {
            this.money = target.money
            this.date = target.date
            this.peopleName = target.peopleName
            this.donation = target.donation
        }
        return orderDao.saveAndFlush(get)
    }


}