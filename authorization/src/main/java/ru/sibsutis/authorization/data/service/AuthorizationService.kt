package ru.sibsutis.authorization.data.service

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sibsutis.authorization.data.model.User
import ru.sibsutis.core.network.KtorClient

@Serializable
private data class Request(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String
)

class AuthorizationService(private val ktorClient: KtorClient) {
    suspend fun getToken(login: String, password: String): User {
        var response: User = ktorClient.client.post("auth/login") {
            setBody(
                Request(login = login, password = password)
            )
        }.body()
        return response
    }
}