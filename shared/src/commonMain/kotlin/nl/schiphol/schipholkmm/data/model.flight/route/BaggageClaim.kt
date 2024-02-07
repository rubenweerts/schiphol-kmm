package nl.schiphol.schipholkmm.data.models.flight.route




data class BaggageClaim(
    val belts: List<String>,
) 

fun BaggageClaim.toBeltsString(): String? = belts.run {
    when (size) {
        0 -> null
        1 -> first()
        else -> "${first()}-${last()}"
    }
}
