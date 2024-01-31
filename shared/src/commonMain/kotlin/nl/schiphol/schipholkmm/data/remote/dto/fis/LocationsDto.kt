package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.Serializable

sealed interface LocationsDto {
    val gate: Gate?
    val pier: Pier?
    val terminal: Terminal?

    @Serializable
    data class Arrival(
        override val gate: Gate?,
        override val pier: Pier?,
        override val terminal: Terminal?,
        val baggageClaims: BaggageClaims?
    ) : LocationsDto {
        @Serializable
        data class BaggageClaims(
            val label: String,
            val belts: List<String>
        )
    }

    @Serializable
    class Departure(
        override val gate: Gate?,
        override val pier: Pier?,
        val nearestLounge: NearestLounge?,
        override val terminal: Terminal?,
        val securityFilter: SecurityFilter?,
        val checkIn: CheckIn?,
        val transferDesks: TransferDesk?,
    ) : LocationsDto {
        @Serializable
        data class CheckIn(
            val from: TimeDto?,
            val to: TimeDto,
            val label: String,
            val rows: List<String>,
            val baggageDropOff: List<BaggageDropOff>?,
        ) {
            @Serializable
            data class InnerCheckIn(val from: TimeDto?, val to: TimeDto, val label: String, val rows: List<String>)
        }

        @Serializable
        data class BaggageDropOff(val from: TimeDto?, val to: TimeDto, val label: String, val rows: List<String>)

        @Serializable
        data class TransferDesk(val label: String)

        @Serializable
        data class SecurityFilter(
            val code: String,
            val terminal: String?
        )
    }

    @Serializable
    data class Gate(
        val label: String,
        val isChanged: Boolean,
        val isOpen: Boolean,
        val isABusGate: Boolean = false,
        val pointOfInterest: PointOfInterest?
    ) {
        @Serializable
        data class PointOfInterest(
            val label: String,
            val bubble: Bubble,
            val location: Location,
            val wayfindingIdentifier: String
        ) {
            @Serializable
            data class Bubble(
                val label: String,
                val code: String
            )

            @Serializable
            data class Location(
                val latitude: String,
                val longitude: String,
                val floor: Int
            )
        }
    }

    @Serializable
    data class Pier(
        val label: String
    )

    @Serializable
    data class NearestLounge(
        val number: String
    )

    @Serializable
    data class Terminal(
        val number: String
    )
}