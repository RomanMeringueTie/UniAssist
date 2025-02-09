package ru.sibsutis.student.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val id: Int,
    val student_id: Int,
    val body: String,
    val mark: Int?
)