package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.models.flight.route.Gate
import nl.schiphol.schipholkmm.data.remote.dto.flight.GateDto
import nl.schiphol.schipholkmm.data.mapping.Mapper

class GateTypeDtoMapper : Mapper<GateDto.Type, Gate.Type>() {
    override fun transform(value: GateDto.Type): Gate.Type = when (value) {
        GateDto.Type.Unknown -> Gate.Type.Unknown
        GateDto.Type.Bus -> Gate.Type.Bus
        GateDto.Type.Regular -> Gate.Type.Regular
    }
}
