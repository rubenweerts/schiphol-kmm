package nl.schiphol.schipholkmm.data.di

import nl.schiphol.schipholkmm.data.local.DatabaseDriverFactory
import nl.schiphol.schipholkmm.data.local.createDatabase
import nl.schiphol.schipholkmm.database.common.SchipholDatabase
import org.koin.dsl.module

actual val platformLocalModule = module {
    single<SchipholDatabase> {
        createDatabase(DatabaseDriverFactory(get()))
    }
}
