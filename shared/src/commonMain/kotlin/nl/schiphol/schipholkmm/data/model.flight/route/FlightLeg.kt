package nl.schiphol.schipholkmm.data.models.flight.route




data class FlightLeg(
    val id: String,
    val flightId: String,
    val number: String,
    val status: FlightLegStatus,
    val departure: FlightDeparture,
    val arrival: FlightArrival,
)
