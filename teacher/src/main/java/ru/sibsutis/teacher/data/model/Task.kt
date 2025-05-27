package ru.sibsutis.teacher.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id")
    val id: String,
    @SerialName("header")
    val header: String,
    @SerialName("body")
    val body: String,
    @SerialName("responses")
    var responses: List<Response> = emptyList()
)