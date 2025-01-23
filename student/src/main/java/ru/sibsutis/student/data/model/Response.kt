package ru.sibsutis.student.data.model

data class Response(
    val id: Int,
    val student_id: Int,
    val body: String,
    val mark: Int
)