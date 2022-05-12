package com.santimattius.template.di

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.*
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.R])
class AppConfigurationTest : KoinTest {
    @Test
    fun verifyKoinApp() {
        koinApplication {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(Dependencies().module)
            checkModules()
        }
    }
}
