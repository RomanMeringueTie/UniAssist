package ru.sibsutis.student.domain

import ru.sibsutis.student.data.model.Class
import ru.sibsutis.student.data.repository.StudentRepository

class GetStudentClassUseCase(private val repository: StudentRepository) {
    suspend operator fun invoke(id: Int): Result<Class> {
        return try {
            val classItem = repository.getClass(id)
            Result.success(classItem)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}