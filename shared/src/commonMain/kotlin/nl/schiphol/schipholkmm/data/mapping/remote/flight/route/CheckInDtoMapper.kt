package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.models.flight.route.CheckIn
import nl.schiphol.schipholkmm.data.remote.dto.flight.CheckInDto
import nl.schiphol.schipholkmm.data.mapping.Mapper

class CheckInDtoMapper : Mapper<CheckInDto, CheckIn>() {
    override fun transform(value: CheckInDto): CheckIn {
        return CheckIn(
            startDateTime = value.startDateTime,
            endDateTime = value.endDateTime,
            rows = value.rows,
        )
    }
}
