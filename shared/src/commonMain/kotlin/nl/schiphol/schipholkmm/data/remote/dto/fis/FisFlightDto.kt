package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface FisFlightDto {
    val links: Links
    val identifier: String
    val pushNotificationTopic: String
    val flightNumber: FlightNumber
    val airline: Airline
    val operator: Airline
    val aircraft: Aircraft?
    val route: RouteDto
    val scheduled: ScheduledDto
    val contactInformation: ContactInformationDto
    val status: FisStatusDto
    val expected: ExpectedDto

    @Serializable
    data class Departure(
        @SerialName("_links") override val links: Links,
        @SerialName("identifier") override val identifier: String,
        @SerialName("subscriptionTopicForNotifications") override val pushNotificationTopic: String,
        @SerialName("flightNumber") override val flightNumber: FlightNumber,
        @SerialName("airline") override val airline: Airline,
        @SerialName("operator") override val operator: Airline,
        @SerialName("aircraft") override val aircraft: Aircraft? = null,
        @SerialName("route") override val route: RouteDto,
        @SerialName("scheduled") override val scheduled: ScheduledDto.Departure,
        @SerialName("contactInformation") override val contactInformation: ContactInformationDto,
        @SerialName("status") override val status: FisStatusDto.DepartingStatus,
        @SerialName("visum") val visum: VisumDto,
        @SerialName("expected") override val expected: ExpectedDto.Departure,
        @SerialName("locations") val locations: LocationsDto.Departure
    ) : FisFlightDto

    @Serializable
    data class Arrival(
        @SerialName("_links") override val links: Links,
        @SerialName("identifier") override val identifier: String,
        @SerialName("subscriptionTopicForNotifications") override val pushNotificationTopic: String,
        @SerialName("flightNumber") override val flightNumber: FlightNumber,
        @SerialName("airline") override val airline: Airline,
        @SerialName("operator") override val operator: Airline,
        @SerialName("aircraft") override val aircraft: Aircraft? = null,
        @SerialName("route") override val route: RouteDto,
        @SerialName("scheduled") override val scheduled: ScheduledDto.Arrival,
        @SerialName("contactInformation") override val contactInformation: ContactInformationDto,
        @SerialName("status") override val status: FisStatusDto.ArrivingStatus,
        @SerialName("expected") override val expected: ExpectedDto.Arrival,
        @SerialName("locations") val locations: LocationsDto.Arrival
    ) : FisFlightDto

    @Serializable
    data class Links(val website: Link, val onlineCheckIn: Link?) {
        @Serializable
        data class Link(val href: String)
    }

    @Serializable
    data class FlightNumber(
        val label: String,
        val mainFlight: AlsoKnownAs?,
        val alsoKnownAs: List<AlsoKnownAs>
    ) {
        @Serializable
        data class AlsoKnownAs(
            val label: String,
            val identifier: String
        )
    }

    @Serializable
    data class Airline(
        val iataCode: String,
        val label: String
    )

    @Serializable
    data class Aircraft(
        val registration: String?,
        val type: Type
    ) {
        @Serializable
        data class Type(
            val label: String,
            val iataCode: String?,
        )
    }
}
