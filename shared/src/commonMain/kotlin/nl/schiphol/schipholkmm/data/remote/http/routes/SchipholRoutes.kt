package org.schiphol.data.remote.http.routes

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.appendPathSegments
import org.schiphol.common.EnvironmentConfig
import org.schiphol.data.remote.dto.flight.GetFlightInformationArguments

object SchipholRoutes : RouteConfig, HostConfigProvider {
    override fun provideHostConfig(environmentConfig: EnvironmentConfig) = when (environmentConfig) {
        EnvironmentConfig.Test,
        EnvironmentConfig.Acceptance,
        -> HostConfig(
            host = "acc.schiphol.dev",
            basePath = "api/mobile-app/",
        )

        EnvironmentConfig.Production -> HostConfig(
            host = "www.schiphol.nl",
            basePath = "api/mobile-app/",
        )
    }


    context (HttpRequestBuilder)
    fun flightRoute(id: String) = url {
        appendPathSegments(
            "flight",
            id,
        )
    }

    context (HttpRequestBuilder)
    fun flightInformation(arguments: GetFlightInformationArguments) = url {
        appendPathSegments(
            "flight",
            arguments.direction.value,
            arguments.formattedDate,
            arguments.number,
        )
    }
}
