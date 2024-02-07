package nl.schiphol.schipholkmm.data.remote.http.routes

import nl.schiphol.schipholkmm.data.common.EnvironmentConfig

object SchipholRoutes : RouteConfig, HostConfigProvider {
    override fun provideHostConfig(environmentConfig: EnvironmentConfig) =
        when (environmentConfig) {
            EnvironmentConfig.Test,
            EnvironmentConfig.Acceptance,
            -> HostConfig(
                host = "acc.schiphol.dev",
                basePath = "api/mobile-app/",
            )

            EnvironmentConfig.Production -> HostConfig(
                host = "www.schiphol.nl",
                basePath = "api/mobile-app/",
            )
        }
}
