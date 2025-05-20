package ru.sibsutis.teacher.domain

interface SendTeacherTaskUseCase {
    suspend operator fun invoke(id: String, header: String, body: String): Result<Unit>
}