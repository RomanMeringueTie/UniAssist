package ru.sibsutis.student.data.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sibsutis.authorization.data.model.FullName

@Serializable
data class ClassModel(
    @SerialName("id")
    val id: String,
    @SerialName("subjectName")
    val subject: String,
    @SerialName("fullName")
    val fullName: FullName,
    @SerialName("startTime")
    val startTime: LocalTime,
    @SerialName("endTime")
    val endTime: LocalTime,
    @SerialName("type")
    val type: ClassType,
    @SerialName("classroom")
    val classroom: String,
    @SerialName("task")
    val task: Task? = null
)