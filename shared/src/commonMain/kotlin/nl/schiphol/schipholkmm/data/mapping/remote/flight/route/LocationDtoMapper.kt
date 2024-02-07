package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.mapping.Mapper
import nl.schiphol.schipholkmm.data.models.flight.route.Location
import nl.schiphol.schipholkmm.data.remote.dto.flight.LocationDto

class LocationDtoMapper(
    private val airportDtoMapper: AirportDtoMapper,
) : Mapper<LocationDto, Location>() {
    override fun transform(value: LocationDto): Location {
        return Location(
            city = value.city,
            timeZone = value.timeZone,
            airport = airportDtoMapper(value.airport),
        )
    }
}
