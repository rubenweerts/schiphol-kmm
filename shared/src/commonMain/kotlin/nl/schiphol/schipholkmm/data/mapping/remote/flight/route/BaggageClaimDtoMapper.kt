package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.mapping.Mapper
import nl.schiphol.schipholkmm.data.models.flight.route.BaggageClaim
import nl.schiphol.schipholkmm.data.remote.dto.flight.BaggageClaimDto
class BaggageClaimDtoMapper : Mapper<BaggageClaimDto, BaggageClaim>() {
    override fun transform(value: BaggageClaimDto): BaggageClaim {
        return BaggageClaim(
            belts = value.belts
        )
    }
}
