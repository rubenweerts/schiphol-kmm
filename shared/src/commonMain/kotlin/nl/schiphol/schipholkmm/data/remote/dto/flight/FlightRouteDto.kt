package org.schiphol.data.remote.dto.flight

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.schiphol.data.remote.dto.fis.FisFlightDto

@Serializable
data class FlightRouteDto(
    @SerialName("identifier")
    val id: String,
    @Contextual val fisFlight: FisFlightDto,
    val legs: List<FlightLegDto>,
)
