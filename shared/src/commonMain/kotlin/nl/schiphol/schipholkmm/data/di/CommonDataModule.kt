package nl.schiphol.schipholkmm.data.di

import nl.schiphol.schipholkmm.data.mapping.di.mapperModule
import nl.schiphol.schipholkmm.data.remote.di.remoteModule
import nl.schiphol.schipholkmm.data.repositories.repositoryModule
import org.koin.dsl.module

fun commonDataModule() = module {
    includes(remoteModule())
    includes(mapperModule())
    includes(repositoryModule())
}
