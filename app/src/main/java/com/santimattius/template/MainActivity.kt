package com.santimattius.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import coil.annotation.ExperimentalCoilApi
import com.santimattius.template.core.presentation.openLink
import com.santimattius.template.ui.screen.HomeScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicturesApplication {
                HomeScreen {
                    openLink(it.link)
                }
            }
        }
    }
}


