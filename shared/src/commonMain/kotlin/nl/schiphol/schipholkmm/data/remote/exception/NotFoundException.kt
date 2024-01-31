package org.schiphol.data.remote.exception

import io.ktor.http.HttpStatusCode

class NotFoundException(
    responseText: String,
    requestUrl: String? = null,
) : RemoteResponseException(status.value, responseText) {
    override val message: String = buildString {
        append("Not found: ${requestUrl}. ")
        append("Status: ${status}.")
    }

    companion object {
        private val status = HttpStatusCode.NotFound
    }
}