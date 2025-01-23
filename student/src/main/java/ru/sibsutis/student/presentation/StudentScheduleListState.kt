package ru.sibsutis.student.presentation

import ru.sibsutis.student.ui.ClassUI

sealed interface StudentScheduleListState {
    data object Loading : StudentScheduleListState
    data class Content(val list: List<ClassUI>) : StudentScheduleListState
    data class Failure(val message: String) : StudentScheduleListState
}