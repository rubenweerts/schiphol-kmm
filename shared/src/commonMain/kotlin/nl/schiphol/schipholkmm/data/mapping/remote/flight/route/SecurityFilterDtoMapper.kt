package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.models.flight.route.SecurityFilter
import nl.schiphol.schipholkmm.data.remote.dto.flight.SecurityFilterDto
import nl.schiphol.schipholkmm.data.mapping.Mapper

class SecurityFilterDtoMapper : Mapper<SecurityFilterDto, SecurityFilter>() {
    override fun transform(value: SecurityFilterDto): SecurityFilter {
        return SecurityFilter(
            code = value.code,
            terminal = value.terminal,
        )
    }
}
