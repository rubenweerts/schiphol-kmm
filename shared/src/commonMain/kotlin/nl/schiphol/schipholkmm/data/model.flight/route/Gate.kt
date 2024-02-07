package nl.schiphol.schipholkmm.data.models.flight.route


data class Gate(
    val name: String,
    val type: Type,
    val isChanged: Boolean,
) {
    enum class Type {
        Unknown,
        Bus,
        Regular,
    }
}
