package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.repository.TeacherRepository

class SendTeacherMarkUseCaseImpl(private val repository: TeacherRepository) : SendTeacherMarkUseCase {
    override suspend fun invoke(id: String, mark: Int): Result<Unit> {
        try {
            val result = repository.sendMark(id, mark)
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}