package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.repository.TeacherRepository

class SendTeacherTaskUseCaseImpl(private val repository: TeacherRepository) : SendTeacherTaskUseCase {
    override suspend fun invoke(id: String, header: String, body: String): Result<Unit> {
        try {
            val result = repository.sendTask(id, header, body)
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}