package org.schiphol.data.remote.dto.timeslot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTimeslotAppointmentRequestDto(
    @SerialName("timeslotId")
    val timeslotId: String,
    @SerialName("flightIdentifier")
    val flightId: String,
    val email: String,
    val people: Int,
)
