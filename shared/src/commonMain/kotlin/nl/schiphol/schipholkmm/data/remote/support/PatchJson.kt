package nl.schiphol.schipholkmm.data.remote.support

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.patch
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend inline fun HttpClient.patchJson(block: HttpRequestBuilder.() -> Unit): HttpResponse =
    patch(HttpRequestBuilder().apply {
        contentType(ContentType.Application.Json)
    }.apply(block))
