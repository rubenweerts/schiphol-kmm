package org.schiphol.data.remote.serializer

import kotlinx.datetime.UtcOffset
import kotlinx.datetime.asTimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.schiphol.data.remote.dto.fis.OffsetInstantDto

object OffsetInstantDtoSerializer : KSerializer<OffsetInstantDto> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OffsetInstant", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): OffsetInstantDto {
        val isoString = decoder.decodeString()
        val instant = isoString.toInstant()
        val utcOffset = isoString.toUtcOffset()
        return OffsetInstantDto(instant, utcOffset)
    }

    override fun serialize(encoder: Encoder, value: OffsetInstantDto) {
        val dateTimeString = value.instant.toLocalDateTime(value.utcOffset.asTimeZone()).toString()
        val offsetString = value.utcOffset.toString()
        encoder.encodeString(dateTimeString + offsetString)
    }
}

/**
 * Converts this string in ISO-8601 format including the time zone offset to an [UtcOffset] value.
 */
private fun String.toUtcOffset(): UtcOffset {
    val isoString = this
    val time = isoString.indexOf('T', ignoreCase = true)
    if (time == -1) return UtcOffset.ZERO // The string is malformed.

    val offset = isoString.indexOfLast { c -> c == '+' || c == '-' }
    val offsetString = if (offset < time) {
        "Z"  // the offset is 'Z' and not +/- something else
    } else {
        val offsetPart = isoString.substring(offset)
        val separator = offsetPart.indexOf(':') // if there is a ':' in the offset, no changes needed
        if (separator != -1) offsetPart else "$offsetPart:00"
    }
    return UtcOffset.parse(offsetString)
}
