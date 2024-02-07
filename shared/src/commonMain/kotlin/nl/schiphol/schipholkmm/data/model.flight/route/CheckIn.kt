package nl.schiphol.schipholkmm.data.models.flight.route


import kotlinx.datetime.Instant





data class CheckIn(
    val startDateTime:  Instant?,
    val endDateTime:  Instant?,
    val rows: List<String>,
) 
