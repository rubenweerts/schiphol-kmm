package nl.schiphol.schipholkmm.data.remote.api

import io.ktor.client.HttpClient
import nl.schiphol.schipholkmm.data.remote.http.routes.RouteConfig

internal abstract class BaseApi<out T : RouteConfig>(protected val routes: T) {
    //    protected abstract val httpClientEngine: HttpClientEngine
    protected abstract val httpClient: HttpClient
}
