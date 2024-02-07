package nl.schiphol.schipholkmm.data.models.flight.route

import kotlinx.datetime.Instant

sealed interface FlightDepartureOrArrival {
    val location: Location
    val terminal: String?
    val gate: Gate?
    val scheduledDateTime: Instant?
    val estimatedDateTime: Instant?
    val actualDateTime: Instant?
}

/**
 * Gets the expected (most relevant) date time of `this` flight departure or arrival.
 * Returns [FlightDepartureOrArrival.actualDateTime] if not `null`, returns [FlightDepartureOrArrival.estimatedDateTime]
 * if not `null`, otherwise returns [FlightDepartureOrArrival.scheduledDateTime].
 */
fun FlightDepartureOrArrival.expectedDateTime(): Instant? {
    return actualDateTime ?: estimatedDateTime ?: scheduledDateTime
}
