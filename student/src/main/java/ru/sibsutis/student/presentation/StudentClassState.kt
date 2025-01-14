package ru.sibsutis.student.presentation

import ru.sibsutis.student.data.model.Class

sealed interface StudentClassState {
    data object Loading : StudentClassState
    data class Content(val classItem: Class) : StudentClassState
    data class Failure(val message: String?) : StudentClassState
}