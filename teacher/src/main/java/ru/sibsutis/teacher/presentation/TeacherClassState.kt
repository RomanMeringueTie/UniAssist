package ru.sibsutis.teacher.presentation

import ru.sibsutis.core.presentation.State
import ru.sibsutis.teacher.ui.ClassUI

data class TeacherClassState (
    val classState: State<ClassUI> = State.Loading,
    val taskState: TaskState = TaskState.Initial,
    val responseState: ResponseState = ResponseState.Initial,
    val isDialogShown: Boolean = false,
    val isResponseShown: Boolean = false,
    val taskValueHeader: String = "",
    val taskValueBody: String = ""
)

sealed interface TaskState {
    data object Initial : TaskState
    data object Loading : TaskState
    data class Failure(val message: String) : TaskState
    data object Content : TaskState
}

sealed interface ResponseState {
    data object Initial : ResponseState
    data object Loading : ResponseState
    data class Failure(val message: String) : ResponseState
    data object Content : ResponseState
}