package ru.sibsutis.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    private var token: String = ""

    fun setToken(_token: String) {
        token = _token
    }

    val client = HttpClient(Android) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(Auth) {
            bearer {
                BearerTokens(token, "")
            }
        }
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
        }

        engine {
            connectTimeout = 30_000
            socketTimeout = 30_000
        }
        defaultRequest {
            url("https://uniassist-backend.onrender.com/api/v1/")
        }

    }
}