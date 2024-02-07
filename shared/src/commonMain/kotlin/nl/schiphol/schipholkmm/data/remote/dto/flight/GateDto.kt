package nl.schiphol.schipholkmm.data.remote.dto.flight

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GateDto(
    val name: String,
    val type: Type,
    val isChanged: Boolean,
) {
    enum class Type {
        @SerialName("unknown") Unknown,
        @SerialName("bus") Bus,
        @SerialName("regular") Regular,
    }
}
