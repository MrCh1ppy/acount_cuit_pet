package com.example.acount_cuit_pet.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import com.example.acount_cuit_pet.component.api.ApiResult
import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.component.log.Slf4j.Companion.log
import com.example.acount_cuit_pet.entity.ProjectOrder
import com.example.acount_cuit_pet.param.order.OrderSaveParam
import com.example.acount_cuit_pet.param.order.OrderSelectParam
import com.example.acount_cuit_pet.param.order.OrderUpdateParam
import com.example.acount_cuit_pet.service.OrderService
import com.example.acount_cuit_pet.vo.OrderPageVo
import com.example.acount_cuit_pet.vo.OrderVo
import io.swagger.annotations.ApiModel
import io.swagger.v3.oas.annotations.Operation
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/order")
@ApiModel("查询订单controller")
@Slf4j
class OrderController : ControllerHelper {
    @Autowired
    lateinit var orderService: OrderService

    @PostMapping("/query")
    @Operation(description = "查询订单")
    @SaCheckLogin
    fun select(@RequestBody @Validated @NotNull param: OrderSelectParam): ApiResult<OrderPageVo> {
        return ApiResult.ok(orderService.selectPage(param))
    }

    @PostMapping("/save")
    @Operation(description = "存储订单")
    @Throws(ProjectException::class)
    @SaCheckLogin
    fun save(@RequestBody @Validated @NotNull param: OrderSaveParam): ApiResult<OrderVo> {
        val order: ProjectOrder? = orderService.save(param)
        log.info("{}已被存储", order)
        return ApiResult.ok(order?.getVo())
    }

    @GetMapping("/drop")
    @Operation(description = "删除订单")
    @SaCheckLogin
    fun drop(@NotNull id: Int): ApiResult<String> {
        orderService.dropOrder(id)
        return ApiResult.ok("")
    }

    @SaCheckLogin
    @PostMapping("/update")
    fun update(@RequestBody @Validated @NotNull param: OrderUpdateParam): ApiResult<OrderVo> {
        val apply = ProjectOrder().apply {
            this.peopleName = param.peopleName
            this.donation = param.isDonation
            this.id = param.id
            this.date = LocalDate.ofInstant(param.date.toInstant(), ZoneId.systemDefault())
            this.title = param.title
        }
        val order = orderService.update(apply)
        return ApiResult.ok(order?.getVo())
    }
}
