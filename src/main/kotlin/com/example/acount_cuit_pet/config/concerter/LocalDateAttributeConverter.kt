package com.example.acount_cuit_pet.config.concerter

import java.sql.Date
import java.time.LocalDate
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateAttributeConverter : AttributeConverter<LocalDate?, Date?> {
    override fun convertToDatabaseColumn(locDate: LocalDate?): Date? {
        return locDate?.let { Date.valueOf(locDate) }
    }

    override fun convertToEntityAttribute(sqlDate: Date?): LocalDate? {
        return sqlDate?.toLocalDate()
    }
}