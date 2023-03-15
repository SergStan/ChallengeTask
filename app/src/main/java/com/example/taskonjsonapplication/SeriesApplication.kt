package com.example.taskonjsonapplication

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class SeriesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            koin.loadModules(
                listOf(
                    seriesModule
                )
            )
        }
    }
}