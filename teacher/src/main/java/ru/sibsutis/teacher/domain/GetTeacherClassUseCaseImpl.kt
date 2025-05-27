package ru.sibsutis.teacher.domain

import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.model.LessonModel
import ru.sibsutis.teacher.data.repository.TeacherRepository

class GetTeacherClassUseCaseImpl (private val repository: TeacherRepository): GetTeacherClassUseCase {
    override suspend fun invoke(id: String): Result<ClassModel> {
        return repositoryCall {
            repository.getClass(id)
        }
    }
}