package ru.sibsutis.student.presentation

sealed interface State<out T> {
    data object Loading : State<Nothing>
    data class Content<T>(val content: T) : State<T>
    data class Failure<T>(val message: String) : State<T>
}