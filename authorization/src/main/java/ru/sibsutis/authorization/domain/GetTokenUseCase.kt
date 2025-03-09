package ru.sibsutis.authorization.domain

import ru.sibsutis.authorization.data.model.User

interface GetTokenUseCase {
    suspend operator fun invoke(login: String, password: String): Result<User>
}
