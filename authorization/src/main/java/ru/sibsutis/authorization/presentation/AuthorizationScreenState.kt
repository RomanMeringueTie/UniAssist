package ru.sibsutis.authorization.presentation

import ru.sibsutis.authorization.data.model.User

data class AuthorizationScreenState(
    val login: String = "",
    val password: String = "",
    val isPasswordShown: Boolean = true,
    val state: LoginState = LoginState.Initial
)

sealed interface LoginState {
    data object Initial : LoginState
    data object Loading : LoginState
    data class Content(val user: User) : LoginState
    data class Failure(val message: String) : LoginState
}