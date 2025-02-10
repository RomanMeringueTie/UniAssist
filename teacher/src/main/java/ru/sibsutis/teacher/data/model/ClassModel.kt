package ru.sibsutis.teacher.data.model

data class ClassModel(
    val id: Int,
    val subject: String,
    val startTime: kotlinx.datetime.LocalTime,
    val endTime: kotlinx.datetime.LocalTime,
    val type: ClassType,
    val group: String,
    val classroom: String,
    val task: Task? = null
)