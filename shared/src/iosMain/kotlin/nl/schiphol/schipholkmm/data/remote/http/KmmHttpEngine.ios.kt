package nl.schiphol.schipholkmm.data.remote.http

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual class PlatformHttpEngine : KmmHttpEngine {
    override fun getEngine(): HttpClientEngineFactory<*> = Darwin
}
