package nl.schiphol.schipholkmm.di

import nl.schiphol.schipholkmm.data.repositories.flight.FlightRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object KoinDependencyProvider : KoinComponent {
    val flightRepository: FlightRepository by inject()
}
