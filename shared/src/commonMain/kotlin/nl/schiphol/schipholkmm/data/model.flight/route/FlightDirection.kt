package nl.schiphol.schipholkmm.data.models.flight.route

enum class FlightDirection(val value: String) {
    Departure("departure"),
    Arrival("arrival"),
    ;

    companion object {
        fun fromValueOrElse(value: String?, orElse: () -> FlightDirection = { Departure }): FlightDirection =
            FlightDirection.entries.firstOrNull { it.value == value?.lowercase() } ?: orElse()
    }
}

fun FlightDirection.isDeparture() = this == FlightDirection.Departure
fun FlightDirection.isArrival() = this == FlightDirection.Arrival
