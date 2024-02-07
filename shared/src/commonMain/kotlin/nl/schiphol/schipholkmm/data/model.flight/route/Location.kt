package nl.schiphol.schipholkmm.data.models.flight.route


import kotlinx.datetime.TimeZone




data class Location(
    val city: String,
    val timeZone: TimeZone,
    val airport: Airport,
) 
