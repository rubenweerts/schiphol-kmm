package org.schiphol.data.remote.dto.timeslot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditTimeslotAppointmentRequestDto(
    @SerialName("timeslotId")
    val timeslotId: String,
    @SerialName("flightIdentifier")
    val flightId: String,
    val people: Int,
)
