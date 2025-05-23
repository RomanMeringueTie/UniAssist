package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.repository.TeacherRepository

class TeacherActionTaskUseCaseImpl(
    private val repository: TeacherRepository
) : TeacherActionTaskUseCase {
    override suspend fun <T> executeAction(action: TeacherAction, data: T): Result<Unit> {
        return when (action) {
            is TeacherAction.SendTask -> {
                repositoryCall {
                    repository.sendTask(action.id, action.title, action.content)
                }
            }
            is TeacherAction.SendMark -> {
                repositoryCall {
                    repository.sendMark(action.id, action.mark)
                }
            }
        }
    }

    private suspend fun <T> repositoryCall(
        block: suspend () -> T
    ): Result<T> {
        return try {
            val result = block()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}