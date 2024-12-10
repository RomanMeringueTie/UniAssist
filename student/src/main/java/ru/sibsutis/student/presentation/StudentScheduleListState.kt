package ru.sibsutis.student.presentation

import ru.sibsutis.student.data.model.Class

sealed interface StudentScheduleListState {
    data object Loading : StudentScheduleListState
    data class Content(val list: List<Class>) : StudentScheduleListState
    data class Failure(val message: String?) : StudentScheduleListState
}