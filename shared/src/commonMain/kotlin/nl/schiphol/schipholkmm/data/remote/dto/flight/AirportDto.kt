package org.schiphol.data.remote.dto.flight

import kotlinx.serialization.Serializable

@Serializable
data class AirportDto(
    val name: String,
    val iataCode: String,
)