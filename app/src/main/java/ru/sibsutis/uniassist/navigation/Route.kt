package ru.sibsutis.uniassist.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object AuthorizationRoute : Route

    @Serializable
    data class BackgroundAuthorizationRoute(val login: String, val password: String) : Route

    @Serializable
    data object ScheduleRoute : Route

    @Serializable
    data object MessageRoute : Route

    @Serializable
    data object ProfileRoute : Route

    @Serializable
    data class ClassRoute(val id: Int) : Route
}