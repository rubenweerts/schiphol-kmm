package nl.schiphol.schipholkmm.data.models.flight.route


import kotlin.jvm.JvmInline


data class Airport(
    val name: String,
    val iataCode: IataCode,
) 

@JvmInline
value class IataCode(val value: String) {
    init {
        //Validate IATA code
        require(value.length == 3) { "An IATA code must always be 3 characters. Given is \"$value\"." }
    }

    override fun toString(): String {
        return value
    }

    val isSchiphol
        get() = this == Schiphol

    companion object {
        val Schiphol = IataCode("AMS")
    }
}
