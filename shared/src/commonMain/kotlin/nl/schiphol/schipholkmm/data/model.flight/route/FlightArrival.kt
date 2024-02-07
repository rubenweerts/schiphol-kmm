package nl.schiphol.schipholkmm.data.models.flight.route


import kotlinx.datetime.Instant


data class FlightArrival(
    override val location: Location,
    override val terminal: String?,
    override val gate: Gate?,
    val baggageClaim: BaggageClaim?,
    override val scheduledDateTime: Instant?,
    override val estimatedDateTime: Instant?,
    override val actualDateTime: Instant?,
) : FlightDepartureOrArrival
