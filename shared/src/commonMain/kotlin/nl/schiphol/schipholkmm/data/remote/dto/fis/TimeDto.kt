package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class TimeDto(
    @Contextual val dateAndTime: OffsetInstantDto,
)
