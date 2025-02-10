package ru.sibsutis.teacher.domain

import kotlinx.datetime.LocalDate
import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.repository.TeacherRepository

class GetTeacherScheduleUseCaseImpl(private val repository: TeacherRepository) :
    GetTeacherScheduleUseCase {
        override suspend operator fun invoke(date: LocalDate): Result<List<ClassModel>> {
            return try {
                val list = repository.getSchedule(date)
                Result.success(list)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}