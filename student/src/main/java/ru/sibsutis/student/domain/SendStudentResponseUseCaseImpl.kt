package ru.sibsutis.student.domain

import ru.sibsutis.student.data.repository.StudentRepository

class SendStudentResponseUseCaseImpl(private val repository: StudentRepository) :
    SendStudentResponseUseCase {
    override suspend fun invoke(id: String, body: String): Result<Unit> {
        try {
            val result = repository.sendResponse(id, body)
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}