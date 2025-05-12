package ru.sibsutis.student.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id")
    val id: Int,
    @SerialName("header")
    val header: String,
    @SerialName("body")
    val body: String,
    @SerialName("responses")
    val responses: List<Response> = emptyList()
)