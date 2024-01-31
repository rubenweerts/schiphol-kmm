package org.schiphol.data.remote.fixtures.timeslot

import org.schiphol.data.remote.fixtures.JsonFixture

object TimeslotBookAppointmentFixture : JsonFixture {
    override fun jsonOk() = """
        {
            "appointmentGroupId": "654129df01f384cc53fe0daf",
            "appointmentIds": [
                "654129df01f384cc53fe0e1d"
            ]
        }
    """.trimIndent()
}
