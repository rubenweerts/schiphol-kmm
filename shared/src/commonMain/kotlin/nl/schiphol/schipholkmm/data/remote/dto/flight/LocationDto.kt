package nl.schiphol.schipholkmm.data.remote.dto.flight

import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable
import nl.schiphol.schipholkmm.data.remote.dto.flight.AirportDto

@Serializable
data class LocationDto(
    val city: String,
    val timeZone: TimeZone,
    val airport: AirportDto,
)
