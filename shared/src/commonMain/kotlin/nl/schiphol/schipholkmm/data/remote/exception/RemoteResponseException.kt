package nl.schiphol.schipholkmm.data.remote.exception

open class RemoteResponseException(
    statusCode: Int,
    responseText: String
) : IllegalStateException("Bad response: $statusCode. Text: \"$responseText\"")
