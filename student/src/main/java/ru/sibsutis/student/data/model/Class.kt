package ru.sibsutis.student.data.model

import kotlinx.datetime.LocalTime

enum class ClassType {
    LECTURE,
    PRACTICE,
    LABORATORY
}

data class Task(val header: String)

data class Class(
    val id: Int,
    val subject: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val type: ClassType,
    val teacher: String,
    val classroom: String,
    val task: Task? = null
)