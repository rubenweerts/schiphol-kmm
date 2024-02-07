package nl.schiphol.schipholkmm.data.remote.http.routes

import nl.schiphol.schipholkmm.data.common.EnvironmentConfig


interface HostConfigProvider {
    fun provideHostConfig(environmentConfig: EnvironmentConfig): HostConfig
}

data class HostConfig(
    val host: String,
    val basePath: String,
)
