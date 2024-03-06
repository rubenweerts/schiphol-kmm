package nl.schiphol.schipholkmm

import nl.schiphol.schipholkmm.data.common.EnvironmentConfig
import nl.schiphol.schipholkmm.data.remote.api.SchipholAppApiImpl
import org.koin.dsl.module

fun mockRemoteModule() = module {
    SchipholAppApiImpl(true, EnvironmentConfig.Test)
}
