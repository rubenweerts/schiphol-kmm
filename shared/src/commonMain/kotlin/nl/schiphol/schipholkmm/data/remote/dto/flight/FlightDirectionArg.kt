package nl.schiphol.schipholkmm.data.remote.dto.flight

enum class FlightDirectionArg(val value: String) {
    Departure("departure"),
    Arrival("arrival"),
    ;

    companion object {
        fun fromValueOrElse(
            value: String?,
            orElse: () -> FlightDirectionArg = {
                error("FlightDirectionArg.fromValueOrElse has no default value provided")
            },
        ): FlightDirectionArg = FlightDirectionArg.entries.firstOrNull {
            it.value.lowercase() == value?.lowercase()
        } ?: orElse()
    }
}
