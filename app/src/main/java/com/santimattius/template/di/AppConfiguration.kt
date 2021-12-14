package com.santimattius.template.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppConfiguration {

    companion object {

        fun init(app: Application) {
            startKoin {
                androidLogger(Level.NONE)
                androidContext(app)
                modules(modules)
            }
        }
    }
}