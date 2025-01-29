package ru.sibsutis.student.presentation

import ru.sibsutis.student.ui.ClassUI


sealed interface StudentClassState {
    data object Loading : StudentClassState
    data class Content(val classItem: ClassUI) : StudentClassState
    data class Failure(val message: String?) : StudentClassState
}