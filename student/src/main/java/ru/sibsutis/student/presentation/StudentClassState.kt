package ru.sibsutis.student.presentation

import ru.sibsutis.core.presentation.State
import ru.sibsutis.student.ui.ClassUI

data class StudentClassState(
    val classState: State<ClassUI> = State.Loading,
    val responseState: ResponseState = ResponseState.Initial,
    val isDialogShown: Boolean = false,
    val responseValue: String = ""
)

sealed interface ResponseState {
    data object Initial : ResponseState
    data object Loading : ResponseState
    data class Failure(val message: String) : ResponseState
    data object Content : ResponseState
}