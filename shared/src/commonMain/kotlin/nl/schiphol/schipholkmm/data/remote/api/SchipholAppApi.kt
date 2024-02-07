package nl.schiphol.schipholkmm.data.remote.api

import kotlinx.datetime.LocalDate
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightRouteDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightDirectionArg
import nl.schiphol.schipholkmm.data.remote.dto.flight.GetFlightInformationArguments

internal interface SchipholAppApi {

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
