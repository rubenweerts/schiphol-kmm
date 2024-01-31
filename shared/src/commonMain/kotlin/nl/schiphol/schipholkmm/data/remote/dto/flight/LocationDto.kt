package org.schiphol.data.remote.dto.flight

import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val city: String,
    val timeZone: TimeZone,
    val airport: AirportDto,
)