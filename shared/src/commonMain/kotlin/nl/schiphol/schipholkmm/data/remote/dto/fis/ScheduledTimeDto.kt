package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ScheduledTimeDto(
    @Contextual val dateAndTime: OffsetInstantDto,
    val isObsolete: Boolean = false,
)