package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.models.flight.route.BaggageClaim
import nl.schiphol.schipholkmm.data.models.flight.route.FlightArrival
import nl.schiphol.schipholkmm.data.models.flight.route.Location
import nl.schiphol.schipholkmm.data.models.flight.route.Gate
import nl.schiphol.schipholkmm.data.remote.dto.flight.BaggageClaimDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightArrivalDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.LocationDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.GateDto
import nl.schiphol.schipholkmm.data.mapping.Mapper

class FlightArrivalDtoMapper(
    private val locationDtoMapper: LocationDtoMapper,
    private val gateDtoMapper: GateDtoMapper,
    private val baggageClaimDtoMapper: BaggageClaimDtoMapper,
) : Mapper<FlightArrivalDto, FlightArrival>() {
    override fun transform(value: FlightArrivalDto): FlightArrival {
        return FlightArrival(
            location = locationDtoMapper(value.location),
            terminal = value.terminal,
            gate = gateDtoMapper(value.gate),
            baggageClaim = baggageClaimDtoMapper(value.baggageClaim),
            scheduledDateTime = value.scheduledDateTime,
            estimatedDateTime = value.estimatedDateTime,
            actualDateTime = value.actualDateTime,
        )
    }
}
