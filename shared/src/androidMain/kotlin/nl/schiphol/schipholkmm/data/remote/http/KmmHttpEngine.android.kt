package nl.schiphol.schipholkmm.data.remote.http

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual class PlatformHttpEngine : KmmHttpEngine {
    override fun getEngine(): HttpClientEngineFactory<*> = CIO
}
