package com.josenaves.iddog

import android.app.Application
import com.josenaves.iddog.common.di.applicationModule
import org.koin.android.ext.android.startKoin

class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))
    }
}
