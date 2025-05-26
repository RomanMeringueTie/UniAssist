package ru.sibsutis.teacher.data.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassModel(
    @SerialName("lesson")
    val lesson: LessonModel,
    @SerialName("task")
    val task: Task? = null,
    @SerialName("solutions")
    val solutions: List<Response> = emptyList()
) {
    init {
        task?.responses = solutions
    }
}

@Serializable
data class LessonModel(
    @SerialName("id")
    val id: String = "",
    @SerialName("subjectName")
    val subject: String,
    @SerialName("startTime")
    val startTime: LocalTime,
    @SerialName("endTime")
    val endTime: LocalTime,
    @SerialName("type")
    val type: ClassType,
    @SerialName("groupName")
    val group: String,
    @SerialName("classroom")
    val classroom: String
)