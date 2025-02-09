package ru.sibsutis.student.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class ClassType {
    LECTURE,
    PRACTICE,
    LABORATORY
}