package ru.sibsutis.authorization.domain

import ru.sibsutis.authorization.data.model.User
import ru.sibsutis.authorization.data.repository.AuthorizationRepository

class GetTokenUseCaseImpl(private val authorizationRepository: AuthorizationRepository) :
    GetTokenUseCase {
    override suspend operator fun invoke(login: String, password: String): Result<User> {
        return try {
            val result = authorizationRepository.getToken(login, password)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}