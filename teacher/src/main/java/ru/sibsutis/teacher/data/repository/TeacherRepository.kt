package ru.sibsutis.teacher.data.repository

import kotlinx.datetime.LocalDate
import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.model.LessonModel

interface TeacherRepository {
    suspend fun getSchedule(date: LocalDate): List<LessonModel>
    suspend fun getClass(id: String): ClassModel
    suspend fun sendTask(id: String, title: String, content: String)
    suspend fun sendMark(id: String, mark: Int)
}