package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.mapping.Mapper
import nl.schiphol.schipholkmm.data.models.flight.route.FlightRoute
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightRouteDto

class FlightRouteDtoMapper(
//    private val fisFlightMapper: Mapper<FisFlightDto, FisFlight>,
    private val flightLegDtoMapper: FlightLegDtoMapper,
) : Mapper<FlightRouteDto, FlightRoute>() {
    override fun transform(value: FlightRouteDto): FlightRoute {
        return FlightRoute(
            id = value.id,
//            fisFlight = fisFlightMapper(value.fisFlight),
            legs = value.legs.map { flightLegDtoMapper(it, value.id) },
        )
    }
}
