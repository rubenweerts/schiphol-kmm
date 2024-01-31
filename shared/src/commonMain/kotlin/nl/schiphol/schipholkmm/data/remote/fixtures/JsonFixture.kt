package org.schiphol.data.remote.fixtures

import io.ktor.http.HttpStatusCode

// TODO: Move all fixtures to the testing srcSet! Most of them are objects and claim unnecessary memory
interface JsonFixture {
    fun jsonOk(): String
    fun jsonError(httpStatusCode: HttpStatusCode): String = """
        {
            "error": "This is a default mock for a error code => $httpStatusCode"
        }
    """.trimIndent()
}
