package org.schiphol.data.remote.dto.flight

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class FlightDepartureDto(
    val location: LocationDto,
    val terminal: String?,
    val checkIn: CheckInDto?,
    val baggageDropOff: List<BaggageDropOffDto>?,
    val securityFilter: SecurityFilterDto?,
    val gate: GateDto?,
    val scheduledDateTime: Instant?,
    val estimatedDateTime: Instant?,
    val actualDateTime: Instant?,
)