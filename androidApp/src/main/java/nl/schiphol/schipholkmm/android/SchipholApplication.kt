package nl.schiphol.schipholkmm.android

import android.app.Application
import nl.schiphol.schipholkmm.data.di.commonDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SchipholApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SchipholApplication)
            androidLogger()
            modules(commonDataModule())
        }
    }
}
