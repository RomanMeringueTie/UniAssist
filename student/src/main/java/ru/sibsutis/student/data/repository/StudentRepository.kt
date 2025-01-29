package ru.sibsutis.student.data.repository

import kotlinx.datetime.LocalDate
import ru.sibsutis.student.data.model.ClassModel

interface StudentRepository {
    suspend fun getSchedule(date: LocalDate): List<ClassModel>
    suspend fun getClass(id: Int): ClassModel
}