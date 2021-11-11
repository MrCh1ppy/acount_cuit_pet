package com.example.acount_cuit_pet.entity

import com.example.acount_cuit_pet.vo.OrderVo
import lombok.EqualsAndHashCode
import java.time.LocalDate
import javax.persistence.*

@Entity
@EqualsAndHashCode
class ProjectOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var money: Double? = null
    var peopleName: String? = null
    var donation: Boolean? = null
    var date: LocalDate? = null
    var title: String? = null

    @Version
    var version: Int? = null

    fun getVo(): OrderVo {
        return OrderVo(
            date = date,
            money = money,
            peopleName = peopleName,
            isDonation = donation,
            title = title
        )
    }
}