package ru.sibsutis.student.domain

import ru.sibsutis.student.data.model.Class
import ru.sibsutis.student.data.repository.StudentRepository

class GetStudentScheduleUseCase(private val repository: StudentRepository) {
    suspend operator fun invoke(date: Int): List<Class> {
        return repository.getSchedule(date)
    }
}