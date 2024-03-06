package nl.schiphol.schipholkmm.data.local

import nl.schiphol.schipholkmm.database.common.SchipholDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal fun localModule() = module {
    single { createDatabase(get()) }
}
