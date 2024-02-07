package nl.schiphol.schipholkmm.data.remote.dto.flight

import kotlinx.datetime.LocalDate

data class GetFlightInformationArguments(
    val direction: FlightDirectionArg,
    val date: LocalDate,
    val number: String,
) {
    val formattedDate: String
        get() = date.toString() //fixme

}
