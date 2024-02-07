package nl.schiphol.schipholkmm.data.remote.dto.fis

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import nl.schiphol.schipholkmm.data.remote.dto.fis.OffsetInstantDto

@Serializable
data class TimeDto(
    @Contextual val dateAndTime: OffsetInstantDto,
)
