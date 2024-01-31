package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.Serializable

@Serializable
data class VisumDto(
    val esta: Info,
    val eta: Info
) {
    @Serializable
    data class Info(
        val isNeeded: Boolean,
        val urlToApply: String
    )
}