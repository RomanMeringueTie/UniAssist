package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.model.ClassModel

interface SendTeacherMarkUseCase {
    suspend operator fun invoke(taskId: String, studentId: String, mark: Int): Result<ClassModel>
}