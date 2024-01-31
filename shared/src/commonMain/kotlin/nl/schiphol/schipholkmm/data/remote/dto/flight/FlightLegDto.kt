package org.schiphol.data.remote.dto.flight

import kotlinx.serialization.Serializable

@Serializable
data class FlightLegDto(
    val id: String,
    val number: String,
    val status: FlightLegStatusDto,
    val departure: FlightDepartureDto,
    val arrival: FlightArrivalDto,
)