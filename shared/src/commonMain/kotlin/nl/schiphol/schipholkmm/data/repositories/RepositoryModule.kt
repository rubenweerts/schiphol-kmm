package nl.schiphol.schipholkmm.data.repositories

import nl.schiphol.schipholkmm.data.mapping.di.mapperModule
import nl.schiphol.schipholkmm.data.repositories.flight.FlightRepository
import nl.schiphol.schipholkmm.data.repositories.flight.FlightRepositoryImpl
import org.koin.dsl.module

internal fun repositoryModule()= module {
    includes(mapperModule())
    single<FlightRepository> { FlightRepositoryImpl(get(), get(), get()) }
}
