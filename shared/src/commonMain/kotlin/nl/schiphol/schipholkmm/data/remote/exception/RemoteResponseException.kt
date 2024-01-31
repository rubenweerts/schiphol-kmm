package org.schiphol.data.remote.exception

open class RemoteResponseException(
    statusCode: Int,
    responseText: String
) : IllegalStateException("Bad response: $statusCode. Text: \"$responseText\"")