package nl.schiphol.schipholkmm.data.repositories.flight

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import at.asitplus.KmmResult
import at.asitplus.KmmResult.Companion.wrap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.FlightRouteDtoMapper
import nl.schiphol.schipholkmm.data.model.flight.PoCFlight
import nl.schiphol.schipholkmm.data.models.flight.route.firstLeg
import nl.schiphol.schipholkmm.data.models.flight.route.lastLeg
import nl.schiphol.schipholkmm.data.remote.api.SchipholAppApi
import nl.schiphol.schipholkmm.database.common.SchipholDatabase

internal class FlightRepositoryImpl(
    private val schipholAppApi: SchipholAppApi,
    private val flightRouteDtoMapper: FlightRouteDtoMapper,
    private val schipholDatabase: SchipholDatabase,
) : FlightRepository {
    override fun addFlight(flightIdentifier: String) {
        schipholDatabase.flightQueries.insertFlight(id = flightIdentifier)
    }

    override fun observeFlights(): Flow<Map<String, KmmResult<PoCFlight>>> =
        schipholDatabase.flightQueries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { localList ->
                localList.associateWith {  refreshFlight(it) }
            }

    override suspend fun removeFlight(flightIdentifier: String) {
        withContext(Dispatchers.IO) {
            schipholDatabase.flightQueries.deleteFlight(flightIdentifier)
        }
    }

    override suspend fun refreshFlight(flightIdentifier: String): KmmResult<PoCFlight> =
        schipholAppApi.getFlight(flightIdentifier)
            .map(flightRouteDtoMapper::invoke)
            .map {
                PoCFlight(
                    id = it.id,
                    departureAirport = it.firstLeg.departure.location.airport.iataCode.value,
                    departureDateTime = it.firstLeg.departure.scheduledDateTime.toString(),
                    arrivalAirport = it.lastLeg.arrival.location.airport.iataCode.value,
                    arrivalDateTime = it.lastLeg.arrival.scheduledDateTime.toString()
                )
            }
            .wrap()

}
