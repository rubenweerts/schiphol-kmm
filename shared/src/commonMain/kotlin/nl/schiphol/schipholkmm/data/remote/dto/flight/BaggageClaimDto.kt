package nl.schiphol.schipholkmm.data.remote.dto.flight

import kotlinx.serialization.Serializable

@Serializable
data class BaggageClaimDto(
    val belts: List<String>,
)
