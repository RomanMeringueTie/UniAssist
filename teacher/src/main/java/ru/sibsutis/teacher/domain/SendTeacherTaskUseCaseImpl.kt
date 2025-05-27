package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.model.LessonModel
import ru.sibsutis.teacher.data.repository.TeacherRepository

class SendTeacherTaskUseCaseImpl(
    private val repository: TeacherRepository,
    private val getTeacherClassUseCase: GetTeacherClassUseCase
) : SendTeacherTaskUseCase {
    override suspend fun invoke(id: String, title: String, content: String): Result<ClassModel> {
        return repositoryCall {
            repository.sendTask(id, title, content)
        }.fold(
            onSuccess = { getTeacherClassUseCase(id) },
            onFailure = { Result.failure(it) }
        )
    }
}