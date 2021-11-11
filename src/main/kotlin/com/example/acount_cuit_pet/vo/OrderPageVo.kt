package com.example.acount_cuit_pet.vo

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.Value
import org.springframework.data.domain.Page

@EqualsAndHashCode(callSuper = true)
@Value
@AllArgsConstructor
@Builder
data class OrderPageVo constructor(
    var orderPage: Page<OrderVo?>? = Page.empty(),
    var res: Double? = null,
    var peopleGroupVos: List<PeopleGroupVo>? = emptyList()
) {}
