package org.schiphol.data.remote.http.routes

import org.schiphol.common.EnvironmentConfig

interface HostConfigProvider {
    fun provideHostConfig(environmentConfig: EnvironmentConfig): HostConfig
}

data class HostConfig(
    val host: String,
    val basePath: String,
)
