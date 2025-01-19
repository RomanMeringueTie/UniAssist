package ru.sibsutis.student.presentation

import ru.sibsutis.student.data.model.ClassModel

sealed interface StudentScheduleListState {
    data object Loading : StudentScheduleListState
    data class Content(val list: List<ClassModel>) : StudentScheduleListState
    data class Failure(val message: String) : StudentScheduleListState
}