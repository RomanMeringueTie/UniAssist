package ru.sibsutis.student.ui

import ru.sibsutis.student.data.model.ClassType

data class ClassUI(
    val id: Int,
    val subject: String,
    val startTime: String,
    val endTime: String,
    val type: ClassType,
    val teacher: String,
    val classroom: String,
    val task: TaskUI?,
)

data class TaskUI(
    val header: String,
    val body: String,
    val responses: List<ResponseUI>?
)

data class ResponseUI(
    val body: String,
    val mark: Int?
)