package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.Serializable

sealed interface ExpectedDto {
    val on: TimeDto
    val isExpectedOnAnotherDate: Boolean
    val isEarly: Boolean
    val isLate: Boolean

    @Serializable
    data class Departure(
        override val on: TimeDto,
        override val isExpectedOnAnotherDate: Boolean,
        override val isEarly: Boolean,
        override val isLate: Boolean,
        val boarding: TimeDto
    ) : ExpectedDto

    @Serializable
    data class Arrival(
        override val on: TimeDto,
        override val isExpectedOnAnotherDate: Boolean,
        override val isEarly: Boolean,
        override val isLate: Boolean,
        val baggageOnBelt: TimeDto?
    ) : ExpectedDto
}