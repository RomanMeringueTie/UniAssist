package ru.sibsutis.teacher.data.model

data class Response (
    val id: Int,
    val studentId: Int,
    val body: String,
    val mark: Int
)