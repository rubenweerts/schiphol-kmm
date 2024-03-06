package nl.schiphol.schipholkmm.android.flight

import nl.schiphol.schipholkmm.data.model.flight.PoCFlight

sealed interface FlightState {

    data object Initial : FlightState

    data object Loading : FlightState

    data class Content(
        val flight: PoCFlight,
    ) : FlightState

    data class Error(
        val throwable: Throwable
    ) : FlightState
}
