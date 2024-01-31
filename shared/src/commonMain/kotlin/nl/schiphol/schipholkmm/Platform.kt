package nl.schiphol.schipholkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform