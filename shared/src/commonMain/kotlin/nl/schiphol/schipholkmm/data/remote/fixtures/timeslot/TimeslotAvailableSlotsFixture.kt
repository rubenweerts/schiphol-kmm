package org.schiphol.data.remote.fixtures.timeslot

import org.schiphol.data.remote.fixtures.JsonFixture

object TimeslotAvailableSlotsFixture : JsonFixture {
    override fun jsonOk() = """
        {
            "data": {
                "slots": [
                    {
                        "id": "some-id",
                        "start": "2023-11-06T15:30:00Z",
                        "end": "2023-11-06T15:45:00Z"
                    },
                    {
                        "id": "other-id",
                        "start": "2023-11-06T15:45:00Z",
                        "end": "2023-11-06T16:00:00Z"
                    }
                ]
            }
        }
    """.trimIndent()
}
