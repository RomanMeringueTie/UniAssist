package ru.sibsutis.teacher.data.model

import kotlinx.datetime.LocalTime

data class ClassModel(
    val id: Int,
    val subject: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val type: ClassType,
    val group: String,
    val classroom: String,
    val task: Task? = null
)