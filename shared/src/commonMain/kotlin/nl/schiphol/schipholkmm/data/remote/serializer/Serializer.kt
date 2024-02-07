package nl.schiphol.schipholkmm.data.remote.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object Serializer {
    @OptIn(ExperimentalSerializationApi::class)
    val json by lazy {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
            serializersModule = SerializersModule
        }
    }
}
