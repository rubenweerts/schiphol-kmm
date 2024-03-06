package nl.schiphol.schipholkmm.data.repositories.flight

import at.asitplus.KmmResult
import kotlinx.coroutines.flow.Flow
import nl.schiphol.schipholkmm.data.model.flight.PoCFlight

interface FlightRepository {
    fun addFlight(flightIdentifier: String)
    fun observeFlights(): Flow<Map<String, KmmResult<PoCFlight>>>
    suspend fun refreshFlight(flightIdentifier: String): KmmResult<PoCFlight>
    suspend fun removeFlight(flightIdentifier: String)
}
