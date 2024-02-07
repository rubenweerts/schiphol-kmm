package nl.schiphol.schipholkmm.data.models.flight.route




data class FlightLegStatus(
    val label: String?,
    val status: Status,
) {
    enum class Status {
        Scheduled,
        AwaitGateOpen,
        GateOpen,
        Boarding,
        GateClosing,
        GateClosed,
        Departed,
        PreparingLanding,
        Landed,
        BaggageOnBelt,
        Completed,
        Diverted,
        Cancelled,
        ;
    }
}

/**
 * Gets whether `this` [FlightLegStatus] is a discontinued flight state.
 * Cancelled and diverted flights are discontinued flight states.
 */
val FlightLegStatus.isDiscontinued
    get() = when (this.status) {
        FlightLegStatus.Status.Cancelled,
        FlightLegStatus.Status.Diverted,
        -> true

        else -> false
    }
