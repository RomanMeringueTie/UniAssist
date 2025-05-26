package ru.sibsutis.teacher.domain

import kotlinx.datetime.LocalDate
import ru.sibsutis.teacher.data.model.LessonModel

interface GetTeacherScheduleUseCase {
    suspend operator fun invoke(date: LocalDate): Result<List<LessonModel>>
}