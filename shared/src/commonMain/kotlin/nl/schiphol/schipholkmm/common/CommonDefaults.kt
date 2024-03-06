package nl.schiphol.schipholkmm.common

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object CommonDefaults {
    fun defaultFlightId(): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val year = now.year
        val month = "${now.monthNumber}".padStart(2, '0')
        val day = "${now.dayOfMonth}".padStart(2, '0')
        val flightNumber = listOf(
            "SQ323",
            "KL1703",
            "KL0959",
            "OS380",
            "KL0767",
        )
        return "D$year$month${day}${flightNumber.random()}"
    }
}
