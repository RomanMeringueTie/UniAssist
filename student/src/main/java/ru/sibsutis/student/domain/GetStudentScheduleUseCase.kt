package ru.sibsutis.student.domain

import kotlinx.datetime.LocalDate
import ru.sibsutis.student.data.model.Class
import ru.sibsutis.student.data.repository.StudentRepository

class GetStudentScheduleUseCase(private val repository: StudentRepository) {
    suspend operator fun invoke(date: LocalDate): Result<List<Class>> {
        return try {
            val list = repository.getSchedule(date)
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}