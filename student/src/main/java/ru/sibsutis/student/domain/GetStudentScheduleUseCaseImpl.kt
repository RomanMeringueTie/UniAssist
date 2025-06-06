package ru.sibsutis.student.domain

import kotlinx.datetime.LocalDate
import ru.sibsutis.student.data.model.LessonModel
import ru.sibsutis.student.data.repository.StudentRepository

class GetStudentScheduleUseCaseImpl(private val repository: StudentRepository) :
    GetStudentScheduleUseCase {
    override suspend operator fun invoke(date: LocalDate): Result<List<LessonModel>> {
        return try {
            val list = repository.getSchedule(date)
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}