package ru.sibsutis.student.data.repository

import kotlinx.datetime.LocalDate
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.LessonModel

interface StudentRepository {
    suspend fun getSchedule(date: LocalDate): List<LessonModel>
    suspend fun getClass(id: String): ClassModel
    suspend fun sendResponse(id: String, body: String)
}