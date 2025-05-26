package ru.sibsutis.student.ui

import ru.sibsutis.student.data.model.ClassType
import ru.sibsutis.student.data.model.Response
import ru.sibsutis.student.data.model.Task

data class ClassUI(
    val id: String,
    val subject: String,
    val startTime: String,
    val endTime: String,
    val type: ClassType,
    val teacher: String,
    val classroom: String,
    val task: Task?,
    val response: Response?
)