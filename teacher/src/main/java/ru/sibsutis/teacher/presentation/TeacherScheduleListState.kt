package ru.sibsutis.teacher.presentation

import ru.sibsutis.teacher.ui.ClassUI

sealed interface TeacherScheduleListState {
    data object Loading : TeacherScheduleListState
    data class Content(val list: List<ClassUI>) : TeacherScheduleListState
    data class Failure(val message: String) : TeacherScheduleListState
}