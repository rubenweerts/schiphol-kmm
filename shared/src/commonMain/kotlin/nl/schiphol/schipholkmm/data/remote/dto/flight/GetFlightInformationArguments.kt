package org.schiphol.data.remote.dto.flight

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

data class GetFlightInformationArguments(
    val direction: FlightDirectionArg,
    val date: LocalDate,
    val number: String,
) {
    val formattedDate: String
        get() = DateTimeFormatter.ISO_DATE.format(date.toJavaLocalDate()) //FIXME: Use shared formatter for consistency

}
