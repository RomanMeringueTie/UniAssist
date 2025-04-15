package ru.sibsutis.authorization.data.repository

import ru.sibsutis.authorization.data.model.User
import ru.sibsutis.authorization.data.service.AuthorizationService


class AuthorizationRepositoryImpl(private val authorizationService: AuthorizationService) :
    AuthorizationRepository {
    override suspend fun getToken(login: String, password: String): User {
        return authorizationService.getToken(login, password)
    }
}