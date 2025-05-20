package ru.sibsutis.student.domain

import ru.sibsutis.student.data.model.ClassModel

interface SendStudentResponseUseCase {
    suspend operator fun invoke(taskId: String, body: String, classId: String): Result<ClassModel>
}
