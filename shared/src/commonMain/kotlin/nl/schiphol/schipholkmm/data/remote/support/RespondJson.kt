package org.schiphol.data.remote.support

import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpResponseData
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.toByteArray


fun MockRequestHandleScope.respondJson(
    content: String,
    status: HttpStatusCode = HttpStatusCode.OK,
    headers: Headers = headersOf(),
): HttpResponseData {
    val jsonHeaders = headers {
        appendAll(headers)
        append("Content-Type", "${ContentType.Application.Json}")
    }
    return respond(ByteReadChannel(content.toByteArray(Charsets.UTF_8)), status, jsonHeaders)
}


fun MockRequestHandleScope.respondJsonError(
    status: HttpStatusCode,
    content: String = status.description,
    headers: Headers = headersOf(),
): HttpResponseData {
    val jsonHeaders = headers {
        appendAll(headers)
        append("Content-Type", "${ContentType.Application.Json}")
    }
    return respond(content, status, jsonHeaders)
}
