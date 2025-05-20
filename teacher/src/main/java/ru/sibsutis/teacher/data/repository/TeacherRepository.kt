package ru.sibsutis.teacher.data.repository

import kotlinx.datetime.LocalDate
import ru.sibsutis.teacher.data.model.ClassModel

interface TeacherRepository {
    suspend fun getSchedule(date: LocalDate): List<ClassModel>
    suspend fun getClass(id: String): ClassModel
    suspend fun sendTask(id: String, header: String, body: String)
    suspend fun sendMark(id: String, mark: Int)
}