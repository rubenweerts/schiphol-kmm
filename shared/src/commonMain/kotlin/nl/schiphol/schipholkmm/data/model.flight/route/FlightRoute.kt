package nl.schiphol.schipholkmm.data.models.flight.route

import kotlinx.collections.immutable.toPersistentList

data class FlightRoute(
    val id: String,
    val legs: List<FlightLeg>,
//    val fisFlight: FisFlight,
) {
    init {
        require(legs.isNotEmpty()) { "The 'legs' list cannot be empty" }

        // Validate presence of required Schiphol leg.
        val schipholLeg = legs.singleOrNull { leg ->
            leg.departure.location.airport.iataCode.isSchiphol || leg.arrival.location.airport.iataCode.isSchiphol
        }
        requireNotNull(schipholLeg) { "There must be exactly one leg that arrives or departs at Schiphol" }
    }

    /**
     * Gets the [legs] sorted as a whole sequential route. Sorted with [sortRouteSequentially].
     */
    val sortedLegs by lazy { legs.sortRouteSequentially() }
}

/**
 * Sorts `this` list of [FlightLeg]s as a whole sequential route. From the route's first departure to the last arrival.
 * Sorts by the [Airport.iataCode] (see [FlightDepartureOrArrival.location]) of every [FlightLeg.departure] and [FlightLeg.arrival].
 *
 * For example:
 * Amsterdam ➔ Aruba ➔ Groningen
 * Groningen ➔ Aruba -> Amsterdam
 *
 * @throws IllegalArgumentException if there is a [FlightLeg] that cannot be matched and sorted based on the [Airport.iataCode]
 */
internal fun List<FlightLeg>.sortRouteSequentially(): List<FlightLeg> {
    if (size <= 1) return this

    // Find the Schiphol leg.
    val schipholLeg = single { leg ->
        leg.departure.location.airport.iataCode.isSchiphol || leg.arrival.location.airport.iataCode.isSchiphol
    }

    // Sort the legs based on the IATA code.
    val sortedLegs = mutableListOf(schipholLeg)
    val unsortedLegs = this.minus(schipholLeg).toMutableList()

    // Sort the unsorted legs until there are no more unsorted legs.
    while (unsortedLegs.isNotEmpty()) {
        val insertLeg = unsortedLegs.find { unsortedLeg ->
            val departureLocation = unsortedLeg.departure.location
            val arrivalLocation = unsortedLeg.arrival.location

            // Check if the leg can be inserted before the sorted leg.
            arrivalLocation.airport.iataCode == sortedLegs.first().departure.location.airport.iataCode
                    || departureLocation.airport.iataCode == sortedLegs.last().arrival.location.airport.iataCode
        }

        // If no leg can be inserted, it means there is an issue with the input data.
        requireNotNull(insertLeg) { "Could not match and sort all legs based on their IATA code" }

        // Insert the leg in the sorted legs list and remove it from the unsorted legs.
        val insertIndex =
            if (insertLeg.arrival.location.airport.iataCode == sortedLegs.first().departure.location.airport.iataCode) {
                0
            } else {
                sortedLegs.size
            }
        sortedLegs.add(insertIndex, insertLeg)
        unsortedLegs.remove(insertLeg)
    }

    return sortedLegs.toPersistentList()
}

/**
 * Returns the first [FlightLeg] of the sequentially sorted legs of `this` route.
 *
 * @throws NoSuchElementException if the list is empty.
 * @see sortRouteSequentially
 */
val FlightRoute.firstLeg: FlightLeg
    get() = sortedLegs.first()

/**
 * Returns the last [FlightLeg] of the sequentially sorted legs of `this` route.
 *
 * @throws NoSuchElementException if the list is empty.
 * @see sortRouteSequentially
 */
val FlightRoute.lastLeg: FlightLeg
    get() = sortedLegs.last()

/**
 * Returns the departure of the whole route. So the first leg's [FlightDeparture].
 *
 * @throws NoSuchElementException if the legs list is empty.
 */
val FlightRoute.departure: FlightDeparture
    get() = firstLeg.departure

/**
 * Gets the arrival of the whole route. So the last leg's [FlightArrival].
 *
 * @throws NoSuchElementException if the legs list is empty.
 */
val FlightRoute.arrival: FlightArrival
    get() = lastLeg.arrival

/**
 * Gets whether this flight departs from Schiphol.
 * So the [departure] leg's [Location.airport] is Schiphol.
 */
val FlightRoute.isDepartureFromSchiphol: Boolean
    get() = departure.location.airport.iataCode.isSchiphol

/**
 * Gets whether this flight arrives at Schiphol.
 * So the [arrival] leg's [Location.airport] is Schiphol.
 */
val FlightRoute.isArrivalAtSchiphol: Boolean
    get() = arrival.location.airport.iataCode.isSchiphol

/**
 * Gets whether `this` flight is a discontinued flight state.
 * Returns `true` if the [FlightLegStatus.isDiscontinued] of any [FlightRoute.legs] is `true`.
 */
val FlightRoute.isDiscontinued: Boolean
    get() = legs.any { leg -> leg.status.isDiscontinued }
