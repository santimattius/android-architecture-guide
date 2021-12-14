package com.santimattius.template

import android.app.Application
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.santimattius.template.di.AppConfiguration
import com.santimattius.template.ui.theme.AndroidarchtemplateTheme

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfiguration.init(app = this)
    }
}

@Composable
fun PicturesApplication(content: @Composable () -> Unit) {
    AndroidarchtemplateTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}