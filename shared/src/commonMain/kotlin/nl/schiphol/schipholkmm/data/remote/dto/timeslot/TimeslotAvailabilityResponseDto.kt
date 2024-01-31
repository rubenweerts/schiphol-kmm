package org.schiphol.data.remote.dto.timeslot

import kotlinx.serialization.Serializable

@Serializable
data class TimeslotAvailabilityResponseDto(
    val data: Data,
) {
    @Serializable
    data class Data(
        val slots: List<TimeslotDto>
    )
}
