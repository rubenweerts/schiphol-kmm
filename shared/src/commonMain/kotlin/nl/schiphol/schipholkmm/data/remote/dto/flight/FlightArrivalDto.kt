package nl.schiphol.schipholkmm.data.remote.dto.flight

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class FlightArrivalDto(
    val location: LocationDto,
    val terminal: String?,
    val gate: GateDto?,
    val baggageClaim: BaggageClaimDto?,
    val scheduledDateTime: Instant?,
    val estimatedDateTime: Instant?,
    val actualDateTime: Instant?,
)
