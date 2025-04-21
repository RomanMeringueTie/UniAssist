package ru.sibsutis.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import ru.sibsutis.core.utils.TokenProvider

class KtorClient(private val tokenProvider: TokenProvider) {

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
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }
        install(
            createClientPlugin("AuthorizationPlugin") {
                onRequest { request, _ ->
                    withContext(Dispatchers.IO) {
                        val authToken = tokenProvider.get()
                        if (authToken != null) {
                            request.headers.append("Authorization", "Bearer $authToken")
                        }
                    }
                }
            },
        )

        engine {
            connectTimeout = 30_000
            socketTimeout = 30_000
        }
        defaultRequest {
            url("https://uniassist-backend.onrender.com/api/v1/")
        }

    }
}