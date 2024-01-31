package org.schiphol.data.remote.dto.fis

import kotlinx.datetime.Instant
import kotlinx.datetime.UtcOffset
import kotlinx.serialization.Serializable

@Serializable
data class OffsetInstantDto(
    val instant: Instant,
    val utcOffset: UtcOffset,
)
