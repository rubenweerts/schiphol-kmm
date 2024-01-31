package org.schiphol.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.schiphol.data.remote.http.routes.RouteConfig

abstract class BaseApi<out T : RouteConfig>(protected val routes: T) {
    protected abstract val httpClientEngine: HttpClientEngine
    protected abstract val httpClient: HttpClient
}
