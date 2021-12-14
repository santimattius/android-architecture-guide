package com.santimattius.template.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.santimattius.template.R
import com.santimattius.template.ui.models.*
import com.santimattius.template.ui.viewmodels.HomeViewModel
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(onClick: (PictureUiModel) -> Unit) {
    val viewModel: HomeViewModel = getViewModel()
    HomeScreen(viewModel.state, onClick)
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(data: LiveData<HomeState>, onClick: (PictureUiModel) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) {
        val currentState: HomeState by data.observeAsState(Loading)
        when (currentState) {
            is Data -> ListOfPicture(
                pictures = (currentState as Data).values,
                onClick = onClick
            )
            Error -> ErrorView(stringResource(R.string.text_error))
            Loading -> LoadingIndicator()
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ListOfPicture(pictures: List<PictureUiModel>, onClick: (PictureUiModel) -> Unit) {
    LazyColumn {
        items(pictures) { picture ->
            PictureCard(picture = picture, modifier = Modifier.clickable {
                onClick(picture)
            })
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PictureCard(picture: PictureUiModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.medium))
    ) {
        Card {
            Column {
                Image(
                    painter = rememberImagePainter(data = picture.imageUrl),
                    contentDescription = picture.author,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .aspectRatio(ratio = (16 / 8).toFloat()),
                )
                Box(
                    modifier = Modifier.padding(
                        dimensionResource(R.dimen.small),
                        dimensionResource(R.dimen.medium)
                    )
                ) {
                    Text(
                        text = picture.author,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 2
                    )
                }
            }
        }
    }
}