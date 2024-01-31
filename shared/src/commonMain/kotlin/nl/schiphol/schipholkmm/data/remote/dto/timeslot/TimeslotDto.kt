package org.schiphol.data.remote.dto.timeslot

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TimeslotDto(
    val id: String,
    val start: Instant,
    val end: Instant,
)
