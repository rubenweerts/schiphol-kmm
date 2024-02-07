package nl.schiphol.schipholkmm.data.common

sealed interface EnvironmentConfig {
    data object Test : EnvironmentConfig

    data object Acceptance : EnvironmentConfig

    data object Production : EnvironmentConfig
}
