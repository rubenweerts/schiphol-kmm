package nl.schiphol.schipholkmm.data.repositories.flight

interface FlightRepository {
    fun getFlight(flightNumber: String)
}
