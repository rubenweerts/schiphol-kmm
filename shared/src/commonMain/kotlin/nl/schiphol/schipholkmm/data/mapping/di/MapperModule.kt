package nl.schiphol.schipholkmm.data.mapping.di

import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.AirportDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.BaggageClaimDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.BaggageDropOffDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.CheckInDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.FlightArrivalDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.FlightDepartureDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.FlightLegDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.FlightLegStatusDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.FlightRouteDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.GateDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.GateTypeDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.LocationDtoMapper
import nl.schiphol.schipholkmm.data.mapping.remote.flight.route.SecurityFilterDtoMapper
import org.koin.dsl.module

internal fun mapperModule() = module {
    single { AirportDtoMapper() }
    single { BaggageClaimDtoMapper() }
    single { BaggageDropOffDtoMapper() }
    single { CheckInDtoMapper() }
    single { FlightArrivalDtoMapper(get(), get(), get()) }
    single {
        FlightDepartureDtoMapper(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    single { FlightLegDtoMapper(get(), get(), get()) }
    single { FlightLegStatusDtoMapper() }
    single { FlightRouteDtoMapper(get()) }
    single { GateDtoMapper(get()) }
    single { GateTypeDtoMapper() }
    single { SecurityFilterDtoMapper() }
    single { LocationDtoMapper(get()) }
}
