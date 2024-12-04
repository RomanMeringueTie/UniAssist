package ru.sibsutis.student.data.repository

import ru.sibsutis.student.data.model.Class
import ru.sibsutis.student.data.service.StudentService

class StudentRepository(private val service: StudentService) {
    suspend fun getSchedule(date: Int): List<Class> {
        return service.getSchedule(date)
    }
}