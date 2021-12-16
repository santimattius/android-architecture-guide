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
import androidx.compose.ui.res.stringResource
import coil.annotation.ExperimentalCoilApi
import com.santimattius.template.R
import com.santimattius.template.ui.models.PictureUiModel
import com.santimattius.template.ui.models.isEmpty
import com.santimattius.template.ui.viewmodels.HomeViewModel
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    onClick: (PictureUiModel) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) {
        when {
            viewModel.state.isLoading -> LoadingIndicator()
            viewModel.state.withError -> ErrorView(stringResource(R.string.text_error))
            viewModel.state.isEmpty -> Text(text = "No hay datos")
            else -> ListOfPicture(
                pictures = viewModel.state.pictures,
                onClick = onClick
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ListOfPicture(
    pictures: List<PictureUiModel>,
    onClick: (PictureUiModel) -> Unit
) {
    LazyColumn {
        items(pictures) { picture ->
            PictureCard(picture = picture, modifier = Modifier.clickable {
                onClick(picture)
            })
        }
    }
}