package nl.schiphol.schipholkmm.data.remote.exception

import io.ktor.http.HttpStatusCode

class ConflictException(
    responseText: String,
    requestUrl: String? = null,
) : RemoteResponseException(status.value, responseText) {
    override val message: String = buildString {
        append("Conflict: ${requestUrl}. ")
        append("Status: $status.")
    }

    companion object {
        private val status = HttpStatusCode.Conflict
    }
}
