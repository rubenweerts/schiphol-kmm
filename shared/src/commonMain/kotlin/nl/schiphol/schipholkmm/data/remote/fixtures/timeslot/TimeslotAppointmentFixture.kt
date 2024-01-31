package org.schiphol.data.remote.fixtures.timeslot

import kotlinx.datetime.Clock
import org.schiphol.data.remote.fixtures.JsonFixture
import kotlin.time.Duration.Companion.minutes

object TimeslotAppointmentFixture : JsonFixture {
    const val id = "some-id"
    val start = Clock.System.now()
    val end = start.plus(15.minutes)
    val people = 3

    override fun jsonOk() = """
        {
            "identifier": "$id",
            "start": "$start",
            "end": "$end",
            "people": $people,
            "qrCodeToken": "some-qr-content",
            "validateToken": "some-token"
        }
    """.trimIndent()
}
