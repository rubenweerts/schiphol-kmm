package org.schiphol.data.remote.api

import kotlinx.datetime.LocalDate
import org.schiphol.data.remote.dto.flight.FlightRouteDto
import org.schiphol.data.remote.dto.flight.FlightDirectionArg
import org.schiphol.data.remote.dto.flight.GetFlightInformationArguments

interface SchipholAppApi {

    suspend fun getFlight(id: String): Result<FlightRouteDto>

    suspend fun getFlight(
        direction: FlightDirectionArg,
        date: LocalDate,
        number: String,
    ): Result<FlightRouteDto> = getFlight(
        GetFlightInformationArguments(
            direction = direction,
            date = date,
            number = number,
        )
    )

    suspend fun getFlight(arguments: GetFlightInformationArguments): Result<FlightRouteDto>
}
