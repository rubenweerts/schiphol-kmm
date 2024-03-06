package nl.schiphol.schipholkmm.data.di

import nl.schiphol.schipholkmm.data.local.localModule
import nl.schiphol.schipholkmm.data.mapping.di.mapperModule
import nl.schiphol.schipholkmm.data.remote.di.remoteModule
import nl.schiphol.schipholkmm.data.repositories.repositoryModule
import nl.schiphol.shared.data.sharedDataModule
import org.koin.dsl.module

fun commonDataModule() = module {
    includes(remoteModule())
    includes(localModule())
    includes(mapperModule())
    includes(repositoryModule())
    includes(sharedDataModule())
    includes(platformLocalModule)
}
