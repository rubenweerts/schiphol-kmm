package nl.schiphol.schipholkmm.data.remote.dto.flight

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.schiphol.schipholkmm.data.remote.dto.fis.FisFlightDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightLegDto

@Serializable
data class FlightRouteDto(
    @SerialName("identifier")
    val id: String,
    @Contextual val fisFlight: FisFlightDto,
    val legs: List<FlightLegDto>,
)
