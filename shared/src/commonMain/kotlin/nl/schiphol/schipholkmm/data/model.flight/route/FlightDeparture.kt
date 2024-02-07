package nl.schiphol.schipholkmm.data.models.flight.route


import kotlinx.datetime.Instant





data class FlightDeparture(
    override val location: Location,
    override val terminal: String?,
    val checkIn: CheckIn?,
    val baggageDropOff: List<BaggageDropOff>?,
    val securityFilter: SecurityFilter?,
    override val gate: Gate?,
    override val scheduledDateTime:  Instant?,
    override val estimatedDateTime:  Instant?,
    override val actualDateTime:   Instant?,
) : FlightDepartureOrArrival
