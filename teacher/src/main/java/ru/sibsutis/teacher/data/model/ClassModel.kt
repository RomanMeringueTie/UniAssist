package ru.sibsutis.teacher.data.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassModel(
    @SerialName("id")
    val id: String,
    @SerialName("subject")
    val subject: String,
    @SerialName("startTime")
    val startTime: LocalTime,
    @SerialName("endTime")
    val endTime: LocalTime,
    @SerialName("type")
    val type: ClassType,
    @SerialName("group")
    val group: String,
    @SerialName("classroom")
    val classroom: String,
    @SerialName("task")
    val task: Task? = null
)