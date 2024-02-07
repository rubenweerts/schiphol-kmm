package nl.schiphol.schipholkmm.data.remote.http

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respondError
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
import nl.schiphol.schipholkmm.data.remote.exception.ConflictException
import nl.schiphol.schipholkmm.data.remote.exception.NotFoundException
import nl.schiphol.schipholkmm.data.remote.fixtures.flight.flightMocks
import nl.schiphol.schipholkmm.data.remote.http.routes.HostConfig
import nl.schiphol.schipholkmm.data.remote.serializer.Serializer

internal object HttpConfig {
    fun defaultHttpEngine(): HttpClientEngineFactory<*> = PlatformHttpEngine().getEngine()
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
                appendIfNameAbsent("Accept-Language", "nl")
            }
        }
    }

    fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultResponseValidator() {
        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                val clientException = exception as? ClientRequestException
                    ?: return@handleResponseExceptionWithRequest
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

    private fun MockEngineConfig.mockEngineConfig() {
        addHandler { requestData ->
            flightMocks(requestData)
                ?: respondError(
                    HttpStatusCode.NotFound,
                    content = "No mock found for \"${requestData.url}\""
                )
        }
    }
}
