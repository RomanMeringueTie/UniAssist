package ru.sibsutis.student.domain

import kotlinx.datetime.LocalDate
import ru.sibsutis.student.data.model.LessonModel

interface GetStudentScheduleUseCase {
    suspend operator fun invoke(date: LocalDate): Result<List<LessonModel>>
}