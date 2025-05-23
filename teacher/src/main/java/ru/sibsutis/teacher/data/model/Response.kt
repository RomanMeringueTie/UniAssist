package ru.sibsutis.teacher.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("id")
    val id: String,
    @SerialName("student_id")
    val studentId: String,
    @SerialName("body")
    val body: String,
    @SerialName("mark")
    val mark: Int?
)