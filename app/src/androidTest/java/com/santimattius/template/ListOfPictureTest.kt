package com.santimattius.template

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.santimattius.template.ui.models.mapping.asUiModels
import com.santimattius.template.ui.screen.LIST_OF_PICTURE_TAG
import com.santimattius.template.ui.screen.ListOfPicture
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoilApi
@ExperimentalFoundationApi
@RunWith(AndroidJUnit4::class)
class ListOfPictureTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

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
            .assertCountEquals(4)
    }

    @Test
    fun testFirstAndLastPictureDescription() {
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
                .assert(hasText("Author 3"))
        }
    }


    private fun getPicturesList() = PictureMother.createPictures().asUiModels()
}