package nl.schiphol.schipholkmm.data.repositories.flight

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.FlightRouteDtoMapper
import nl.schiphol.schipholkmm.data.models.flight.route.FlightRoute
import nl.schiphol.schipholkmm.data.remote.api.SchipholAppApi

internal class FlightRepositoryImpl(
    private val schipholAppApi: SchipholAppApi,
    private val flightRouteDtoMapper: FlightRouteDtoMapper
) : FlightRepository {

    override fun observeFlight(): Flow<FlightRoute?> = flow {
        emit(null)
    }

    override suspend fun fetchFlight(): FlightRoute  {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val year = now.year
        val month = "${now.monthNumber}".padStart(2, '0')
        val day = "${now.dayOfMonth}".padStart(2, '0')
        val flightIdentifier = "D$year$month${day}SQ323"
        return schipholAppApi.getFlight(flightIdentifier).map(flightRouteDtoMapper::invoke).getOrThrow()
    }
}
