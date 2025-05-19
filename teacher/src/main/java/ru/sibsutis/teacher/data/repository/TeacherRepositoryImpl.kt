package ru.sibsutis.teacher.data.repository

import kotlinx.datetime.LocalDate
import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.service.TeacherService

class TeacherRepositoryImpl(private val service: TeacherService) : TeacherRepository {
    override suspend fun getSchedule(date: LocalDate): List<ClassModel> {
        return service.getSchedule(date)
    }

    override suspend fun getClass(id: String): ClassModel {
        return service.getClass(id)
    }
}