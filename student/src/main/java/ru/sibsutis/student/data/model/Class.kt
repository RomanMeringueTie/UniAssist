package ru.sibsutis.student.data.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

enum class ClassType {
    LECTURE,
    PRACTICE,
    LABORATORY
}

@Immutable
data class Task(val header: String)

@Immutable
data class Class(
    val id: LocalDate,
    val subject: String,
    val startTime: Long,
    val endTime: Long,
    val type: ClassType,
    val teacher: String,
    val classroom: String,
    val task: Task
)