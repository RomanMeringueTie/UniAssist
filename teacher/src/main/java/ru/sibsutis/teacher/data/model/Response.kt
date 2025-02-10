package ru.sibsutis.teacher.data.model

data class Response (
    val id: Int,
    val student_id: Int,
    val body: String,
    val mark: Int
)