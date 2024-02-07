package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.mapping.Mapper
import nl.schiphol.schipholkmm.data.models.flight.route.Gate
import nl.schiphol.schipholkmm.data.remote.dto.flight.GateDto

class GateDtoMapper(
    private val gateTypeDtoMapper: GateTypeDtoMapper,
) : Mapper<GateDto, Gate>() {
    override fun transform(value: GateDto): Gate {
        return Gate(
            name = value.name,
            type = gateTypeDtoMapper(value.type),
            isChanged = value.isChanged,
        )
    }
}
