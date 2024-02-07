package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.models.flight.route.BaggageDropOff
import nl.schiphol.schipholkmm.data.models.flight.route.CheckIn
import nl.schiphol.schipholkmm.data.models.flight.route.FlightDeparture
import nl.schiphol.schipholkmm.data.models.flight.route.Gate
import nl.schiphol.schipholkmm.data.models.flight.route.Location
import nl.schiphol.schipholkmm.data.models.flight.route.SecurityFilter
import nl.schiphol.schipholkmm.data.remote.dto.flight.BaggageDropOffDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.CheckInDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightDepartureDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.GateDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.LocationDto
import nl.schiphol.schipholkmm.data.remote.dto.flight.SecurityFilterDto
import nl.schiphol.schipholkmm.data.mapping.Mapper

class FlightDepartureDtoMapper(
    private val locationDtoMapper:LocationDtoMapper,
    private val checkInDtoMapper: CheckInDtoMapper,
    private val baggageDropOffDtoMapper: BaggageDropOffDtoMapper,
    private val securityFilterDtoMapper: SecurityFilterDtoMapper,
    private val gateDtoMapper: GateDtoMapper,
) : Mapper<FlightDepartureDto, FlightDeparture>() {
    override fun transform(value: FlightDepartureDto): FlightDeparture {
        return FlightDeparture(
            location = locationDtoMapper(value.location),
            terminal = value.terminal,
            checkIn = checkInDtoMapper(value.checkIn),
            baggageDropOff = baggageDropOffDtoMapper(value.baggageDropOff),
            securityFilter = securityFilterDtoMapper(value.securityFilter),
            gate = gateDtoMapper(value.gate),
            scheduledDateTime = value.scheduledDateTime,
            estimatedDateTime = value.estimatedDateTime,
            actualDateTime = value.actualDateTime,
        )
    }
}
