package ru.sibsutis.teacher.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ClassType {
    @SerialName("LECTURE")
    LECTURE,
    @SerialName("PRACTICE")
    PRACTICE,
    @SerialName("LABORATORY")
    LABORATORY
}