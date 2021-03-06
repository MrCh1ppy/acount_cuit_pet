package com.example.acount_cuit_pet.specfication.order

import com.example.acount_cuit_pet.entity.ProjectOrder
import com.example.acount_cuit_pet.param.order.OrderSelectParam
import com.google.common.base.Strings
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate
import javax.persistence.Transient
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class OrderSelectSpecification constructor(param: OrderSelectParam) :
    Specification<ProjectOrder?> {
    @Transient
    private val param: OrderSelectParam
    override fun toPredicate(
        root: Root<ProjectOrder?>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val predicateList: MutableList<Predicate> = ArrayList()
        if (!Strings.isNullOrEmpty(param.peopleName)) {
            predicateList.add(
                criteriaBuilder.like(root["peopleName"], "%" + param.peopleName + "%")
            )
        }
        if (!Strings.isNullOrEmpty(param.title)) {
            predicateList.add(
                criteriaBuilder.like(root["title"], "%" + param.title + "%")
            )
        }
        if (param.isDonation != null) {
            predicateList.add(
                criteriaBuilder.equal(root.get<Boolean>("donation"), param.isDonation)
            )
        }
        if (param.minMoney != null || param.maxMoney != null) {
            val minValue=param.minMoney?:Double.MIN_VALUE
            val maxValue = param.maxMoney?:Double.MAX_VALUE
            predicateList.add(criteriaBuilder.between(root["money"], minValue, maxValue))
        }
        if (param.month != null && param.year != null) {
            val tempYear=param.year?:throw NullPointerException()
            val tempMonth=param.month?:throw NullPointerException();
            val maxYear=if(tempMonth!=12)tempYear else tempYear+1
            val maxMonth=if (tempMonth!=12)tempMonth+1 else 1
            val min: LocalDate = LocalDate.of(tempYear, tempMonth, 1)
            val max: LocalDate = LocalDate.of(maxYear, maxMonth, 1)
            predicateList.add(criteriaBuilder.between(root["date"], min, max))
        }
        if (param.year != null && param.month == null) {
            val tempYear=param.year?:throw NullPointerException()
            val min = LocalDate.of(tempYear, 1, 1)
            val max = LocalDate.of(tempYear + 1, 1, 1)
            predicateList.add(criteriaBuilder.between(root["date"], min, max))
        }
        val array = predicateList.toTypedArray()
        return criteriaBuilder.and(*array)
    }

    init {
        this.param = param
    }
}
