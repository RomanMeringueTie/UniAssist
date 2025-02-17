package ru.sibsutis.student.data.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassModel(
    @SerialName("id")
    val id: Int,
    @SerialName("subject")
    val subject: String,
    @SerialName("startTime")
    val startTime: LocalTime,
    @SerialName("endTime")
    val endTime: LocalTime,
    @SerialName("type")
    val type: ClassType,
    @SerialName("teacher")
    val teacher: String,
    @SerialName("classroom")
    val classroom: String,
    @SerialName("task")
    val task: Task? = null
)