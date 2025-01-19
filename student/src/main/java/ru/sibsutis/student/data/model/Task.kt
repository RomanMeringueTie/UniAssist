package ru.sibsutis.student.data.model

data class Task(
    val id: Int,
    val header: String,
    val body: String,
    val responses: List<Response>? = null
)