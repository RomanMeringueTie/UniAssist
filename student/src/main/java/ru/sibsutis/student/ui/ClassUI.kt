package ru.sibsutis.student.ui

data class ClassUI(
    val id: Int,
    val subject: String,
    val startTime: String,
    val endTime: String,
    val type: String,
    val teacher: String,
    val classroom: String,
    val taskHeader: String?
)