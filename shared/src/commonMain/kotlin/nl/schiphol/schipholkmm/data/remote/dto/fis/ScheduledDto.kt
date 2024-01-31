package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.Serializable

sealed interface ScheduledDto {
    val on: ScheduledTimeDto

    @Serializable
    data class Departure(override val on: ScheduledTimeDto, val toBeAtSchiphol: TimeDto?) : ScheduledDto

    @Serializable
    data class Arrival(override val on: ScheduledTimeDto) : ScheduledDto
}