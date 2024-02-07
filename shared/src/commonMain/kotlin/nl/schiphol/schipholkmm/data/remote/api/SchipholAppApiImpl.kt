package nl.schiphol.schipholkmm.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import nl.schiphol.schipholkmm.data.common.EnvironmentConfig
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightRouteDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.GetFlightInformationArguments
import nl.schiphol.schipholkmm.data.remote.http.HttpConfig
import nl.schiphol.schipholkmm.data.remote.http.HttpConfig.defaultContentNegotiationConfig
import nl.schiphol.schipholkmm.data.remote.http.HttpConfig.defaultLoggingConfig
import nl.schiphol.schipholkmm.data.remote.http.HttpConfig.defaultRequestConfig
import nl.schiphol.schipholkmm.data.remote.http.HttpConfig.defaultResponseValidator
import nl.schiphol.schipholkmm.data.remote.http.routes.SchipholRoutes

internal class SchipholAppApiImpl(
    isDebug: Boolean,
    environmentConfig: EnvironmentConfig,
) : BaseApi<SchipholRoutes>(SchipholRoutes), SchipholAppApi {
    override val httpClient = HttpClient(HttpConfig.defaultHttpEngine()) {
        println(HttpConfig.defaultHttpEngine()::class.qualifiedName)
        defaultLoggingConfig(isDebug)
        defaultContentNegotiationConfig()
        defaultRequestConfig(routes.provideHostConfig(environmentConfig))
        defaultResponseValidator()
    }

    override suspend fun getFlight(id: String): Result<FlightRouteDto> = runCatching {
        httpClient.get {
            url {
                appendPathSegments(
                    "flight",
                    id,
                )
            }
        }.body()
    }

    override suspend fun getFlight(arguments: GetFlightInformationArguments): Result<FlightRouteDto> =
        runCatching {
            httpClient.get {
                url {
                    appendPathSegments(
                        "flight",
                        arguments.direction.value,
                        arguments.formattedDate,
                        arguments.number,
                    )
                }
            }.body()
        }
}
