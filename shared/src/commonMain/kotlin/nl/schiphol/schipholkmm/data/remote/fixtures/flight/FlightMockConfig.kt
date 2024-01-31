package org.schiphol.data.remote.fixtures.flight

import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.fullPath
import org.schiphol.data.remote.support.respondJson

fun MockRequestHandleScope.flightMocks(requestData: HttpRequestData): HttpResponseData? {
    return when {
        requestData.url.fullPath.endsWith("flight/${FlightRouteFixture.id}") ||
                (requestData.url.fullPath.contains("flight") &&
                        requestData.url.fullPath.endsWith(FlightRouteFixture.number)) -> respondJson(FlightRouteFixture.jsonOk())

        else -> null
    }
}
