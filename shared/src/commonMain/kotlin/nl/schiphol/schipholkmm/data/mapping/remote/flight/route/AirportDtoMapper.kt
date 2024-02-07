package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.mapping.Mapper
import nl.schiphol.schipholkmm.data.models.flight.route.Airport
import nl.schiphol.schipholkmm.data.models.flight.route.IataCode
import nl.schiphol.schipholkmm.data.remote.dto.flight.AirportDto

class AirportDtoMapper : Mapper<AirportDto, Airport>() {
    override fun transform(value: AirportDto): Airport {
        return Airport(
            name = value.name,
            iataCode = IataCode(value.iataCode),
        )
    }
}
