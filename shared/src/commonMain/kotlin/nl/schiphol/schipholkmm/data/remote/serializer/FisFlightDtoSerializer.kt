package nl.schiphol.schipholkmm.data.remote.serializer

import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.jsonObject
import nl.schiphol.schipholkmm.data.remote.dto.fis.FisFlightDto

object FisFlightDtoSerializer : JsonContentPolymorphicSerializer<FisFlightDto>(FisFlightDto::class) {
    override fun selectDeserializer(element: JsonElement) = when (element.jsonObject["visum"]) {
        null, JsonNull -> FisFlightDto.Arrival.serializer()
        else -> FisFlightDto.Departure.serializer()
    }
}
