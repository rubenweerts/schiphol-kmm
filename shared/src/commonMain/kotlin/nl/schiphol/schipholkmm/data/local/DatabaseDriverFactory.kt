package nl.schiphol.schipholkmm.data.local

import app.cash.sqldelight.db.SqlDriver
import nl.schiphol.schipholkmm.database.common.SchipholDatabase

expect class DatabaseDriverFactory {
    fun createDriver() :SqlDriver
}

internal fun createDatabase(databaseDriverFactory: DatabaseDriverFactory): SchipholDatabase{
    val driver = databaseDriverFactory.createDriver()
    val database = SchipholDatabase(driver)
    return database
}
