package com.santimattius.template.ui.screen

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.santimattius.template.MainActivity
import com.santimattius.template.MainApplication
import com.santimattius.template.PicturesApplication
import com.santimattius.template.objectmothers.PictureMother
import com.santimattius.template.ui.models.mapping.asUiModels
import com.santimattius.template.utils.KoinRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.annotation.Config

@ExperimentalCoilApi
@ExperimentalFoundationApi
@RunWith(AndroidJUnit4::class)
@Config(
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.S_V2],
    instrumentedPackages = ["androidx.loader.content"],
    application = MainApplication::class
)
class ListOfPictureTest : KoinTest {

    @get:Rule
    val koinRule = KoinRule.androidx()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPictureListScreenIfPicturesAreEmpty() {
        composeTestRule.setContent {
            PicturesApplication {
                ListOfPicture(emptyList())
            }
        }
        composeTestRule.onNodeWithTag(LIST_OF_PICTURE_TAG)
            .onChildren()
            .assertCountEquals(0)
    }

    @Test
    fun testPictureListScreenIfPicturesAreNotEmpty() {
        composeTestRule.setContent {
            PicturesApplication {
                ListOfPicture(getPicturesList())
            }
        }
        composeTestRule.onNodeWithTag(LIST_OF_PICTURE_TAG)
            .onChildren()
            .assertCountEquals(2)
    }

    @Test
    fun testFirstAndLastPictureAuthor() {
        composeTestRule.setContent {
            PicturesApplication {
                ListOfPicture(getPicturesList())
            }
        }

        composeTestRule.apply {
            onNodeWithTag(LIST_OF_PICTURE_TAG)
                .onChildren()
                .onFirst()
                .assert(hasText("Author 0"))

            onNodeWithTag(LIST_OF_PICTURE_TAG)
                .onChildren()
                .onLast()
                .assert(hasText("Author 1"))
        }
    }

    private fun getPicturesList() = PictureMother.createPictures().asUiModels()
}
