package ru.sibsutis.teacher.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("id")
    val id: Int,
    @SerialName("student_id")
    val studentId: Int,
    @SerialName("body")
    val body: String,
    @SerialName("mark")
    val mark: Int?
)