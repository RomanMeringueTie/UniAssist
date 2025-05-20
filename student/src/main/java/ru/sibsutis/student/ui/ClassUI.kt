package ru.sibsutis.student.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.student.data.model.ClassType

data class ClassUI(
    val id: String,
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
    val responses: ImmutableList<ResponseUI> = persistentListOf<ResponseUI>()
)

data class ResponseUI(
    val body: String,
    val mark: Int?
)