package ru.sibsutis.teacher.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sibsutis.authorization.data.model.FullName

@Serializable
data class Response(
    @SerialName("id")
    val id: String,
    @SerialName("body")
    val body: String,
    @SerialName("fullName")
    val fullName: FullName,
    @SerialName("mark")
    val mark: Int?
)