package nl.schiphol.schipholkmm.di

import nl.schiphol.schipholkmm.data.repositories.flight.FlightRepository
import nl.schiphol.shared.data.TestRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object KoinDependencyProvider : KoinComponent {
    val flightRepository: FlightRepository by inject()
    val testRepository: TestRepository by inject()
}
