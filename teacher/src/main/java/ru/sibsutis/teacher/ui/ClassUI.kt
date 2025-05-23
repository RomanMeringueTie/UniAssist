package ru.sibsutis.teacher.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.sibsutis.teacher.data.model.ClassType

data class ClassUI (
    val id: String,
    val subject: String,
    val startTime: String,
    val endTime: String,
    val type: ClassType,
    val group: String,
    val classroom: String,
    val task: TaskUI?
)

data class TaskUI(
    val header: String,
    val body: String,
    val responses: ImmutableList<ResponseUI> = persistentListOf<ResponseUI>()
)

data class ResponseUI(
    val id: String,
    val studentId: String,
    val body: String,
    val mark: Int?
)