package ru.sibsutis.student.data.model

enum class ClassType {
    LECTURE,
    PRACTICE,
    LABORATORY
}

data class Task(val header: String)

data class Class (
    val id: Int,
    val subject: String,
    val startTime: Long,
    val endTime: Long,
    val type: ClassType,
    val teacher: String,
    val classroom: String,
    val task: Task
)