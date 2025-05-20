package ru.sibsutis.student.domain

import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.repository.StudentRepository

class SendStudentResponseUseCaseImpl(private val repository: StudentRepository) :
    SendStudentResponseUseCase {
    override suspend fun invoke(taskId: String, body: String, classId: String): Result<ClassModel> {
        try {
            repository.sendResponse(taskId, body)
            val result = repository.getClass(classId)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}