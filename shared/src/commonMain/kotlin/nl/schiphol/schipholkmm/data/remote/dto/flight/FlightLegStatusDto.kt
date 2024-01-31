package org.schiphol.data.remote.dto.flight

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlightLegStatusDto(
    val label: String?,
    @SerialName("name") val status: Status,
) {
    enum class Status {
        @SerialName("scheduled") Scheduled,
        @SerialName("awaitGateOpen") AwaitGateOpen,
        @SerialName("gateOpen") GateOpen,
        @SerialName("boarding") Boarding,
        @SerialName("gateClosing") GateClosing,
        @SerialName("gateClosed") GateClosed,
        @SerialName("departed") Departed,
        @SerialName("preparingLanding") PreparingLanding,
        @SerialName("landed") Landed,
        @SerialName("baggageOnBelt") BaggageOnBelt,
        @SerialName("completed") Completed,
        @SerialName("diverted") Diverted,
        @SerialName("cancelled") Cancelled,
        ;
    }
}