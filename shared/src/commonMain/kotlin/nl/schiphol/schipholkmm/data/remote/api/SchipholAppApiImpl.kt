package org.schiphol.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.schiphol.common.EnvironmentConfig
import org.schiphol.data.remote.dto.flight.FlightRouteDto
import org.schiphol.data.remote.dto.flight.GetFlightInformationArguments
import org.schiphol.data.remote.http.HttpConfig.defaultContentNegotiationConfig
import org.schiphol.data.remote.http.HttpConfig.defaultHttpEngine
import org.schiphol.data.remote.http.HttpConfig.defaultLoggingConfig
import org.schiphol.data.remote.http.HttpConfig.defaultRequestConfig
import org.schiphol.data.remote.http.HttpConfig.defaultResponseValidator
import org.schiphol.data.remote.http.HttpConfig.mockHttpEngine
import org.schiphol.data.remote.http.routes.SchipholRoutes

class SchipholAppApiImpl(
    isDebug: Boolean,
    environmentConfig: EnvironmentConfig,
) : BaseApi<SchipholRoutes>(SchipholRoutes), SchipholAppApi {
    override val httpClientEngine = when (environmentConfig) {
        EnvironmentConfig.Acceptance,
        EnvironmentConfig.Production,
        -> defaultHttpEngine()

        EnvironmentConfig.Test -> mockHttpEngine()
    }

    override val httpClient = HttpClient(httpClientEngine) {
        defaultLoggingConfig(isDebug)
        defaultContentNegotiationConfig()
        defaultRequestConfig(routes.provideHostConfig(environmentConfig))
        defaultResponseValidator()
    }

    override suspend fun getFlight(id: String): Result<FlightRouteDto> = runCatching {
        httpClient.get {
            routes.flightRoute(id)
        }.body()
    }

    override suspend fun getFlight(arguments: GetFlightInformationArguments): Result<FlightRouteDto> = runCatching {
        httpClient.get {
            routes.flightInformation(arguments)
        }.body()
    }
}
