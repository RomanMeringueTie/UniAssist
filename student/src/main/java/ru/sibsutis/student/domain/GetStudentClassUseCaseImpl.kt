package ru.sibsutis.student.domain

import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.repository.StudentRepository

class GetStudentClassUseCaseImpl(private val repository: StudentRepository): GetStudentClassUseCase {
    override suspend operator fun invoke(id: String): Result<ClassModel> {
        return try {
            val classItem = repository.getClass(id)
            Result.success(classItem)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}