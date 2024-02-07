package nl.schiphol.schipholkmm.data.models.flight.route


import kotlinx.datetime.Instant





data class BaggageDropOff(
    val startDateTime:  Instant?,
    val endDateTime:  Instant?,
    val rows: List<String>,
) 
