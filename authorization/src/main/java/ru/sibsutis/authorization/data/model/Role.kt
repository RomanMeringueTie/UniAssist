package ru.sibsutis.authorization.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    @SerialName("STUDENT")
    Student,
    @SerialName("TEACHER")
    Teacher
}