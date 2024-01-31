package org.schiphol.data.remote.api

import org.schiphol.data.remote.dto.timeslot.TimeslotAppointmentDto
import org.schiphol.data.remote.dto.timeslot.TimeslotAvailabilityResponseDto

interface TimeslotApi {

    suspend fun getAvailableTimeslots(
        flightId: String,
        people: Int,
    ): Result<TimeslotAvailabilityResponseDto>

    suspend fun getAppointment(
        id: String,
        token: String,
    ): Result<TimeslotAppointmentDto>

    suspend fun createAppointment(
        timeslotId: String,
        flightId: String,
        email: String,
        peopleQuantity: Int,
    ): Result<TimeslotAppointmentDto>

    suspend fun editAppointment(
        id: String,
        token: String,
        timeslotId: String,
        flightId: String,
        peopleQuantity: Int,
    ): Result<TimeslotAppointmentDto>

    suspend fun cancelAppointment(
        id: String,
        token: String,
    ): Result<Unit>
}
