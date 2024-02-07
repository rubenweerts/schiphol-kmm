package nl.schiphol.schipholkmm.data.repositories.flight

import kotlinx.coroutines.flow.Flow
import nl.schiphol.schipholkmm.data.models.flight.route.FlightRoute

interface FlightRepository {
    fun observeFlight(): Flow<FlightRoute?>
    suspend fun fetchFlight(): FlightRoute
}
