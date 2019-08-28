package com.josenaves.iddog

import android.app.Application
import com.josenaves.iddog.common.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()

            //inject Android context
            androidContext(this@DogApplication)

            // use modules
            modules(applicationModule)
        }
    }
}
