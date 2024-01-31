package org.schiphol.data.remote.dto.flight

import kotlinx.serialization.Serializable

@Serializable
data class SecurityFilterDto(
    val code: String,
    val terminal: String?,
)
