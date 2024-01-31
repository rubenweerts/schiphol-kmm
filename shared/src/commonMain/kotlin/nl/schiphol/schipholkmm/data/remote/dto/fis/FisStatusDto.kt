package org.schiphol.data.remote.dto.fis

import kotlinx.serialization.Serializable

sealed interface FisStatusDto {
    val label: String
    val name: String
    val isFinal: Boolean

    @Serializable
    data class DepartingStatus(
        override val label: String,
        override val name: String,
        override val isFinal: Boolean,
        val willNeverDepart: Boolean? = false,
    ): FisStatusDto

    @Serializable
    data class ArrivingStatus(
        override val label: String,
        override val name: String,
        override val isFinal: Boolean,
        val willNeverArrive: Boolean? = false,
    ): FisStatusDto
}