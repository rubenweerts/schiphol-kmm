package nl.schiphol.schipholkmm.data.remote.dto.fis

import kotlinx.serialization.Serializable

@Serializable
data class RouteDto(
    val direction: Direction,
    val from: RouteInfo,
    val to: RouteInfo,
    val stops: List<RouteInfo>,
    val isASchengenRoute: Boolean
) {
    @Serializable
    data class Direction(
        val code: String,
        val label: String
    )

    @Serializable
    data class RouteInfo(
        val iataCode: String,
        val label: String,
    )
}
