package nl.schiphol.schipholkmm.data.remote.support

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend inline fun HttpClient.postJson(block: HttpRequestBuilder.() -> Unit): HttpResponse =
    post(HttpRequestBuilder().apply {
        contentType(ContentType.Application.Json)
    }.apply(block))
