package ru.sibsutis.teacher.data.repository

import kotlinx.datetime.LocalDate
import ru.sibsutis.teacher.data.model.ClassModel

interface TeacherRepository {
    suspend fun getSchedule(date: LocalDate): List<ClassModel>
    suspend fun getClass(id: String): ClassModel
}