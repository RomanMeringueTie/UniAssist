package ru.sibsutis.authorization.presentation

data class AuthorizationScreenState(
    val login: String = "",
    val password: String = "",
    val isPasswordShown: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)