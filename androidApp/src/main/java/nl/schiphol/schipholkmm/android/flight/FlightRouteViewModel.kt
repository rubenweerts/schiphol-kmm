package nl.schiphol.schipholkmm.android.flight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import nl.schiphol.schipholkmm.data.repositories.flight.FlightRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FlightRouteViewModel : ViewModel(), KoinComponent {
    private val flightRepository: FlightRepository by inject()

    val state = MutableStateFlow<FlightState>(FlightState.Initial)

    fun addFlight(flightIdentifier: String) {
        flightRepository.addFlight(flightIdentifier)
    }

    fun observeFlights() = flightRepository.observeFlights()

    fun removeFlight(flightIdentifier: String) {
        viewModelScope.launch {
            flightRepository.removeFlight(flightIdentifier)
        }
    }
}
