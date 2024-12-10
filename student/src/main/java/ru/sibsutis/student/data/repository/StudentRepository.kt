package ru.sibsutis.student.data.repository

import ru.sibsutis.student.data.model.Class
import ru.sibsutis.student.data.service.StudentService
import java.time.LocalDate

interface StudentRepository {
    suspend fun getSchedule(date: LocalDate): List<Class>
}

class StudentRepositoryImpl(private val service: StudentService) : StudentRepository {
    override suspend fun getSchedule(date: LocalDate): List<Class> {
        return service.getSchedule(date)
    }
}