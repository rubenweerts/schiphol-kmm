package org.schiphol.data.remote.fixtures.timeslot

import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpMethod
import io.ktor.http.content.OutgoingContent
import io.ktor.http.fullPath
import org.schiphol.data.remote.fixtures.flight.FlightRouteFixture
import org.schiphol.data.remote.support.respondJson

fun MockRequestHandleScope.timeslotMocks(requestData: HttpRequestData): HttpResponseData? =
    when {
        requestData.url.fullPath.contains("available-slots/${FlightRouteFixture.id}") &&
                requestData.url.parameters.contains("people") ->
            respondJson(TimeslotAvailableSlotsFixture.jsonOk())

        requestData.url.fullPath.endsWith("appointment/${TimeslotAppointmentFixture.id}") &&
                requestData.method == HttpMethod.Get &&
                requestData.headers.contains("token") ->
            respondJson(TimeslotAppointmentFixture.jsonOk())

        requestData.url.fullPath.endsWith("appointment") &&
                requestData.method == HttpMethod.Post &&
                requestData.body !is OutgoingContent.NoContent ->
            respondJson(TimeslotAppointmentFixture.jsonOk())

        requestData.url.fullPath.endsWith("appointment/${TimeslotAppointmentFixture.id}") &&
                requestData.method == HttpMethod.Patch &&
                requestData.body !is OutgoingContent.NoContent ->
            respondJson(TimeslotAppointmentFixture.jsonOk())

        requestData.url.fullPath.endsWith("appointment/${TimeslotAppointmentFixture.id}") &&
                requestData.method == HttpMethod.Delete &&
                requestData.headers.contains("token") -> respondJson("")

        else -> null
    }
