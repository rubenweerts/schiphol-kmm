package nl.schiphol.schipholkmm.data.model.flight

/**
 * Stripped down version for this PoC
 */
data class PoCFlight(
    val id: String,
    val departureAirport: String?,
    val departureDateTime: String?,
    val arrivalAirport: String?,
    val arrivalDateTime: String?,
)
