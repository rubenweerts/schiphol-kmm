package nl.schiphol.schipholkmm.data.remote.dto.flight

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class BaggageDropOffDto(
    val startDateTime: Instant?,
    val endDateTime: Instant?,
    val rows: List<String>,
)
