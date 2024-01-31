package org.schiphol.data.remote.http.routes

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments
import org.schiphol.common.EnvironmentConfig
import org.schiphol.data.remote.dto.timeslot.CreateTimeslotAppointmentRequestDto
import org.schiphol.data.remote.dto.timeslot.EditTimeslotAppointmentRequestDto

object TimeslotRoutes : RouteConfig, HostConfigProvider {
    override fun provideHostConfig(environmentConfig: EnvironmentConfig) = when (environmentConfig) {
        EnvironmentConfig.Test,
        EnvironmentConfig.Acceptance,
        -> HostConfig(
            host = "acc.schiphol.dev",
            basePath = "api/timeslots/",
        )

        EnvironmentConfig.Production -> HostConfig(
            host = "www.schiphol.nl",
            basePath = "api/timeslots/",
        )
    }

    context(HttpRequestBuilder)
    fun availableTimeslots(
        flightId: String,
        people: Int,
    ) = url {
        appendPathSegments(
            "available-slots",
            flightId,
        )
        parameters.apply {
            append("people", people.toString())
        }
    }

    context (HttpRequestBuilder)
    fun appointment(id: String, token: String) = url {
        appendPathSegments(
            "appointment",
            id,
        )
        headers {
            append("token", token)
        }
    }

    context(HttpRequestBuilder)
    fun appointment(body: CreateTimeslotAppointmentRequestDto) = url {
        appendPathSegments("appointment")
        setBody(body)
    }

    context(HttpRequestBuilder)
    fun appointment(
        id: String,
        token: String,
        body: EditTimeslotAppointmentRequestDto,
    ) = url {
        appendPathSegments(
            "appointment",
            id,
        )
        headers {
            append("token", token)
        }
        setBody(body)
    }
}
