package ru.sibsutis.authorization.data.service

import kotlinx.coroutines.delay
import ru.sibsutis.authorization.data.model.Role
import ru.sibsutis.authorization.data.model.User
import ru.sibsutis.core.network.KtorClient

class AuthorizationService(ktorClient: KtorClient) {
    suspend fun getToken(login: String, password: String): User {
        delay(2000)
        return User(
            token = "AmazingToken",
            fullName = "Иванов Иван Иванович",
            role = Role.Student,
            unit = "ИВ-222",
        )
    }
}