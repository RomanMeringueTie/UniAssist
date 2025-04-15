package ru.sibsutis.authorization.data.repository

import ru.sibsutis.authorization.data.model.User

interface AuthorizationRepository {
    suspend fun getToken(login: String, password: String): User
}