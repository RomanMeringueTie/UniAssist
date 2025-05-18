package ru.sibsutis.authorization.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("token")
    val token: String,
    @SerialName("fullName")
    val fullName: FullName,
    @SerialName("role")
    val role: Role,
    @SerialName("unit")
    val unit: String,
)

@Serializable
data class FullName(
    val lastName: String,
    val firstName: String,
    val middleName: String
)