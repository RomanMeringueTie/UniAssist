package ru.sibsutis.teacher.ui

import ru.sibsutis.teacher.data.model.ClassType

data class ClassUI (
    val id: Int,
    val subject: String,
    val startTime: String,
    val endTime: String,
    val type: ClassType,
    val group: String,
    val classroom: String,
    val taskHeader: String?
)