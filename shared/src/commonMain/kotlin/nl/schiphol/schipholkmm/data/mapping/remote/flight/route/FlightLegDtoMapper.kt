package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.models.flight.route.FlightArrival
import nl.schiphol.schipholkmm.data.models.flight.route.FlightDeparture
import nl.schiphol.schipholkmm.data.models.flight.route.FlightLeg
import nl.schiphol.schipholkmm.data.models.flight.route.FlightLegStatus
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightArrivalDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightDepartureDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightLegDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightLegStatusDto
import nl.schiphol.schipholkmm.data.mapping.Mapper

class FlightLegDtoMapper(
    private val flightLegStatusDtoMapper: FlightLegStatusDtoMapper,
    private val departureDtoMapper: FlightDepartureDtoMapper,
    private val arrivalDtoMapper: FlightArrivalDtoMapper,
) : Mapper<FlightLegDto, FlightLeg>() {

    operator fun invoke(value: FlightLegDto, flightId: String): FlightLeg {
        return transform(value, flightId)
    }

    fun transform(value: FlightLegDto, flightId: String): FlightLeg {
        return FlightLeg(
            id = value.id,
            flightId = flightId,
            number = value.number,
            status = flightLegStatusDtoMapper(value.status),
            departure = departureDtoMapper(value.departure),
            arrival = arrivalDtoMapper(value.arrival),
        )
    }

    @Deprecated("Unsupported, use overload with additional 'flightId' instead", ReplaceWith("transform(value, flightId)"))
    override fun transform(value: FlightLegDto): FlightLeg {
        throw UnsupportedOperationException("Unsupported, use overload with additional 'flightId' instead")
    }
}
