package org.schiphol.data.remote.serializer

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

val SerializersModule = SerializersModule {
    contextual(OffsetInstantDtoSerializer)
    contextual(FisFlightDtoSerializer)
    contextual(OffsetDateTimeSerializer)
}
