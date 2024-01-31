package org.schiphol.data.remote.dto.flight

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CheckInDto(
    val startDateTime: Instant?,
    val endDateTime: Instant?,
    val rows: List<String>,
)