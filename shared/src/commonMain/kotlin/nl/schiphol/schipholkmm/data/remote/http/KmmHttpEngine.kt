package nl.schiphol.schipholkmm.data.remote.http

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineFactory

interface KmmHttpEngine {
    fun getEngine(): HttpClientEngineFactory<*>
}

expect class PlatformHttpEngine(): KmmHttpEngine
