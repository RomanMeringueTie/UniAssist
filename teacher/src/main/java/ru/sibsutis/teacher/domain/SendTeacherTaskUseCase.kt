package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.model.ClassModel

interface SendTeacherTaskUseCase {
    suspend operator fun invoke(id: String, title: String, content: String): Result<ClassModel>
}