package nl.schiphol.schipholkmm.data.mapping.remote.flight.route

import nl.schiphol.schipholkmm.data.models.flight.route.FlightLegStatus
import nl.schiphol.schipholkmm.data.remote.dto.flight.FlightLegStatusDto
import nl.schiphol.schipholkmm.data.mapping.Mapper

class FlightLegStatusDtoMapper : Mapper<FlightLegStatusDto, FlightLegStatus>() {
    override fun transform(value: FlightLegStatusDto): FlightLegStatus {
        return FlightLegStatus(
            label = value.label,
            status = when (value.status) {
                FlightLegStatusDto.Status.Scheduled -> FlightLegStatus.Status.Scheduled
                FlightLegStatusDto.Status.AwaitGateOpen -> FlightLegStatus.Status.AwaitGateOpen
                FlightLegStatusDto.Status.GateOpen -> FlightLegStatus.Status.GateOpen
                FlightLegStatusDto.Status.Boarding -> FlightLegStatus.Status.Boarding
                FlightLegStatusDto.Status.GateClosing -> FlightLegStatus.Status.GateClosing
                FlightLegStatusDto.Status.GateClosed -> FlightLegStatus.Status.GateClosed
                FlightLegStatusDto.Status.Departed -> FlightLegStatus.Status.Departed
                FlightLegStatusDto.Status.PreparingLanding -> FlightLegStatus.Status.PreparingLanding
                FlightLegStatusDto.Status.Landed -> FlightLegStatus.Status.Landed
                FlightLegStatusDto.Status.BaggageOnBelt -> FlightLegStatus.Status.BaggageOnBelt
                FlightLegStatusDto.Status.Completed -> FlightLegStatus.Status.Completed
                FlightLegStatusDto.Status.Diverted -> FlightLegStatus.Status.Diverted
                FlightLegStatusDto.Status.Cancelled -> FlightLegStatus.Status.Cancelled
            }
        )
    }
}
