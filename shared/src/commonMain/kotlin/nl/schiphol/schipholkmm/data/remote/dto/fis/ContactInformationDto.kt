package nl.schiphol.schipholkmm.data.remote.dto.fis

import kotlinx.serialization.Serializable

@Serializable
data class ContactInformationDto(
    val name: String?,
    val phoneNumber: String?,
    val alternatePhoneNumber: String?,
    val website: String?,
    val emailAddress: String?
)
