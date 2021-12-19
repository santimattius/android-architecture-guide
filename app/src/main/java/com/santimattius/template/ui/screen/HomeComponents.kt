package com.santimattius.template.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.santimattius.template.R
import com.santimattius.template.ui.models.PictureUiModel


@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .testTag("LoadingIndicator")
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String) {
    Box(
        modifier = Modifier
            .testTag("ErrorView")
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(message)
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