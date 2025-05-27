package ru.sibsutis.teacher.domain

import kotlinx.datetime.LocalDate
import ru.sibsutis.teacher.data.model.LessonModel
import ru.sibsutis.teacher.data.repository.TeacherRepository

class GetTeacherScheduleUseCaseImpl(private val repository: TeacherRepository) :
    GetTeacherScheduleUseCase {
        override suspend operator fun invoke(date: LocalDate): Result<List<LessonModel>> {
            return repositoryCall {
                repository.getSchedule(date)
            }
        }
}