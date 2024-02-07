package nl.schiphol.schipholkmm.android.flight

import nl.schiphol.schipholkmm.data.models.flight.route.FlightRoute

sealed interface FlightState {

    data object Initial : FlightState

    data object Loading : FlightState

    data class Content(
        val flightRoute: FlightRoute,
    ) : FlightState

    data class Error(
        val throwable: Throwable
    ) : FlightState
}
