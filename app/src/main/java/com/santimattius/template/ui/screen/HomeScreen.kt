package com.santimattius.template.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import coil.annotation.ExperimentalCoilApi
import com.santimattius.template.R
import com.santimattius.template.ui.models.HomeState
import com.santimattius.template.ui.models.PictureUiModel
import com.santimattius.template.ui.models.isEmpty
import com.santimattius.template.ui.viewmodels.HomeViewModel


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onClick: (PictureUiModel) -> Unit,
) {
    HomeScreen(viewModel.state, onClick)
}

@ExperimentalCoilApi
@Composable
private fun HomeScreen(
    state: HomeState,
    onClick: (PictureUiModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) {
        when {
            state.isLoading -> LoadingIndicator()
            state.withError -> ErrorView(stringResource(R.string.text_error))
            state.isEmpty -> ErrorView(stringResource(R.string.text_empty))
            else -> ListOfPicture(
                pictures = state.pictures,
                onClick = onClick
            )
        }
    }
}

const val LIST_OF_PICTURE_TAG = "list_of_picture_test_tag"

@ExperimentalCoilApi
@Composable
fun ListOfPicture(
    pictures: List<PictureUiModel>,
    onClick: (PictureUiModel) -> Unit = {},
) {
    LazyColumn(modifier = Modifier.testTag(LIST_OF_PICTURE_TAG)) {
        items(pictures) { picture ->
            PictureCard(picture = picture, modifier = Modifier.clickable {
                onClick(picture)
            })
        }
    }
}
