package com.santimattius.template.di

import android.app.Application
import org.koin.core.logger.Level

object AppConfiguration {

    fun init(app: Application) {
        initKoin(app) {
            level(Level.NONE)
            modules(modulesDefinitions)
        }
    }
}
