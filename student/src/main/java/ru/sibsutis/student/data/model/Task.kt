package ru.sibsutis.student.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val header: String,
    val body: String,
    val responses: List<Response>? = null
)