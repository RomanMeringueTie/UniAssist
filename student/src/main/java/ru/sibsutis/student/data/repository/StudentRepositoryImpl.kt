package ru.sibsutis.student.data.repository

import kotlinx.datetime.LocalDate
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.service.StudentService

class StudentRepositoryImpl(private val service: StudentService) : StudentRepository {
    override suspend fun getSchedule(date: LocalDate): List<ClassModel> {
        return service.getSchedule(date)
    }
}