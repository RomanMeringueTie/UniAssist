package ru.sibsutis.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import java.net.InetSocketAddress
import java.net.Proxy

class KtorClient {

    val client = HttpClient(Android) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation) {
            json()
        }
        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
        defaultRequest {
            url("https://example.com/")
        }
    }
}