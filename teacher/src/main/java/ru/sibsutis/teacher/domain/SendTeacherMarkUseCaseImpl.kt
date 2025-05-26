package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.repository.TeacherRepository

class SendTeacherMarkUseCaseImpl(
    private val repository: TeacherRepository,
    private val getTeacherClassUseCase: GetTeacherClassUseCase
) : SendTeacherMarkUseCase {
    override suspend fun invoke(taskId: String, studentId: String, mark: Int): Result<ClassModel> {
       return repositoryCall {
            repository.sendMark(studentId, mark)
        }.fold(
            onSuccess = { getTeacherClassUseCase(taskId) },
            onFailure = { Result.failure(it) }
        )
    }
}