package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.mapping.Mapper
import nl.schiphol.schipholkmm.data.models.flight.route.BaggageDropOff
import nl.schiphol.schipholkmm.data.remote.dto.flight.BaggageDropOffDto

class BaggageDropOffDtoMapper: Mapper<BaggageDropOffDto, BaggageDropOff>() {
    override fun transform(value: BaggageDropOffDto): BaggageDropOff {
        return BaggageDropOff(
            startDateTime = value.startDateTime,
            endDateTime = value.endDateTime,
            rows = value.rows
        )
    }
}
