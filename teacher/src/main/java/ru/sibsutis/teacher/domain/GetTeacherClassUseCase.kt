package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.model.ClassModel

interface GetTeacherClassUseCase {
    suspend operator fun invoke(id: String): Result<ClassModel>
}