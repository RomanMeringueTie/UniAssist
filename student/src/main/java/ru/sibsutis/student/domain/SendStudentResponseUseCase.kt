package ru.sibsutis.student.domain

interface SendStudentResponseUseCase {
    suspend operator fun invoke(id: String, body: String): Result<Unit>
}
