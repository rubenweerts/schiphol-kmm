package nl.schiphol.schipholkmm.di

import nl.schiphol.schipholkmm.data.di.commonDataModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(commonDataModule())
    }
}
