package org.schiphol.data.remote.http

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respondError
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import org.schiphol.data.remote.exception.ConflictException
import org.schiphol.data.remote.exception.NotFoundException
import org.schiphol.data.remote.fixtures.flight.flightMocks
import org.schiphol.data.remote.fixtures.timeslot.timeslotMocks
import org.schiphol.data.remote.http.routes.HostConfig
import org.schiphol.data.remote.serializer.Serializer
import java.util.Locale

object HttpConfig {
    fun defaultHttpEngine(): HttpClientEngine = OkHttp.create { okHttpEngineConfig() }
    fun mockHttpEngine(): HttpClientEngine = MockEngine.create { mockEngineConfig() }

    fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultLoggingConfig(isDebug: Boolean) {
        if (!isDebug) return
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.SIMPLE
        }
    }

    fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultContentNegotiationConfig() {
        install(ContentNegotiation) {
            json(
                json = Serializer.json,
                contentType = ContentType.Application.Json,
            )
        }
    }

    fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultRequestConfig(hostConfig: HostConfig) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = hostConfig.host
                path(hostConfig.basePath)
            }
            headers.apply {
                appendIfNameAbsent("Accept-Language", Locale.getDefault().language)
            }
        }
    }

    fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultResponseValidator() {
        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                val exceptionResponse = clientException.response

                when (exceptionResponse.status) {
                    HttpStatusCode.NotFound -> {
                        val exceptionResponseText = exceptionResponse.bodyAsText()
                        throw NotFoundException(
                            exceptionResponseText,
                            requestUrl = exceptionResponse.call.request.url.toString(),
                        )
                    }
                    HttpStatusCode.Conflict -> {
                        val exceptionResponseText = exceptionResponse.bodyAsText()
                        throw ConflictException(
                            exceptionResponseText,
                            requestUrl = exceptionResponse.call.request.url.toString(),
                        )
                    }
                }
            }
        }
    }

    private fun OkHttpConfig.okHttpEngineConfig() {
        // Currently no additional config (interceptors / auth / tokens / ect) for the default engine.
    }

    private fun MockEngineConfig.mockEngineConfig() {
        addHandler { requestData ->
            flightMocks(requestData)
                ?: timeslotMocks(requestData)
                ?: respondError(
                    HttpStatusCode.NotFound,
                    content = "No mock found for \"${requestData.url}\""
                )
        }
    }
}
