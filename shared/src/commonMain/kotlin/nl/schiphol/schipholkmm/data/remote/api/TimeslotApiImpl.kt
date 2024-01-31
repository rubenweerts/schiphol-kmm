package org.schiphol.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import org.schiphol.common.EnvironmentConfig
import org.schiphol.data.remote.dto.timeslot.CreateTimeslotAppointmentRequestDto
import org.schiphol.data.remote.dto.timeslot.EditTimeslotAppointmentRequestDto
import org.schiphol.data.remote.dto.timeslot.TimeslotAppointmentDto
import org.schiphol.data.remote.dto.timeslot.TimeslotAvailabilityResponseDto
import org.schiphol.data.remote.http.HttpConfig
import org.schiphol.data.remote.http.HttpConfig.defaultContentNegotiationConfig
import org.schiphol.data.remote.http.HttpConfig.defaultLoggingConfig
import org.schiphol.data.remote.http.HttpConfig.defaultRequestConfig
import org.schiphol.data.remote.http.HttpConfig.defaultResponseValidator
import org.schiphol.data.remote.http.routes.TimeslotRoutes
import org.schiphol.data.remote.support.patchJson
import org.schiphol.data.remote.support.postJson

class TimeslotApiImpl(
    isDebug: Boolean,
    environmentConfig: EnvironmentConfig,
) : BaseApi<TimeslotRoutes>(TimeslotRoutes), TimeslotApi {

    override val httpClientEngine = when (environmentConfig) {
        EnvironmentConfig.Acceptance,
        EnvironmentConfig.Production,
        -> HttpConfig.defaultHttpEngine()

        EnvironmentConfig.Test -> HttpConfig.mockHttpEngine()
    }

    override val httpClient = HttpClient(httpClientEngine) {
        defaultLoggingConfig(isDebug)
        defaultContentNegotiationConfig()
        defaultRequestConfig(routes.provideHostConfig(environmentConfig))
        defaultResponseValidator()
    }

    override suspend fun getAvailableTimeslots(
        flightId: String,
        people: Int,
    ): Result<TimeslotAvailabilityResponseDto> = runCatching {
        httpClient.get {
            routes.availableTimeslots(
                flightId = flightId,
                people = people,
            )
        }.body()
    }

    override suspend fun getAppointment(
        id: String,
        token: String,
    ): Result<TimeslotAppointmentDto> = runCatching {
        httpClient.get {
            routes.appointment(
                id = id,
                token = token,
            )
        }.body()
    }

    override suspend fun createAppointment(
        timeslotId: String,
        flightId: String,
        email: String,
        peopleQuantity: Int,
    ): Result<TimeslotAppointmentDto> = runCatching {
        val requestBody = CreateTimeslotAppointmentRequestDto(
            timeslotId = timeslotId,
            flightId = flightId,
            email = email,
            people = peopleQuantity,
        )

        httpClient.postJson {
            routes.appointment(body = requestBody)
        }.body()
    }

    override suspend fun editAppointment(
        id: String,
        token: String,
        timeslotId: String,
        flightId: String,
        peopleQuantity: Int,
    ): Result<TimeslotAppointmentDto> = runCatching {
        val requestBody = EditTimeslotAppointmentRequestDto(
            timeslotId = timeslotId,
            flightId = flightId,
            people = peopleQuantity,
        )

        httpClient.patchJson {
            routes.appointment(
                id = id,
                token = token,
                body = requestBody,
            )
        }.body()
    }

    override suspend fun cancelAppointment(
        id: String,
        token: String,
    ): Result<Unit> = runCatching {
        httpClient.delete {
            routes.appointment(
                id = id,
                token = token,
            )
        }.body()
    }
}
