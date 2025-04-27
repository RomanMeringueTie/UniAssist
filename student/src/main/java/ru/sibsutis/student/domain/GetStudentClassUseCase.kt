package ru.sibsutis.student.domain

import ru.sibsutis.student.data.model.ClassModel

interface GetStudentClassUseCase {
    suspend operator fun invoke(id: String): Result<ClassModel>
}