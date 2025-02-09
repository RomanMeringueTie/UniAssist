package ru.sibsutis.student.presentation

sealed interface State<T> {
    class Loading<T> : State<T>
    data class Content<T>(val content: T) : State<T>
    data class Failure<T>(val message: String) : State<T>
}