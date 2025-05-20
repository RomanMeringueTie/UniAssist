package ru.sibsutis.teacher.domain

interface SendTeacherMarkUseCase {
    suspend operator fun invoke(id: String, mark: Int): Result<Unit>
}