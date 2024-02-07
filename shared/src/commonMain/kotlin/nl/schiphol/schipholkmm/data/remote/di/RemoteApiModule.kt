package nl.schiphol.schipholkmm.data.remote.di

import nl.schiphol.schipholkmm.data.common.EnvironmentConfig
import nl.schiphol.schipholkmm.data.remote.api.SchipholAppApi
import nl.schiphol.schipholkmm.data.remote.api.SchipholAppApiImpl
import org.koin.dsl.module
internal fun remoteModule() = module {
    single<SchipholAppApi> {
        SchipholAppApiImpl(true, EnvironmentConfig.Acceptance)
    }
}
