package org.schiphol.data.remote.dto.timeslot

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeslotAppointmentDto(
    @SerialName("identifier")
    val id: String,
    val start: Instant,
    val end: Instant,
    val qrCodeToken: String,
    val validateToken: String,
    val people: Int?,
)
