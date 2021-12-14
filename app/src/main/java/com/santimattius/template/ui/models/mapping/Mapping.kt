package com.santimattius.template.ui.models.mapping

import com.santimattius.template.domain.entities.Picture
import com.santimattius.template.ui.models.PictureUiModel


internal fun List<Picture>.asUiModels() = map { it.asUiModel() }

internal fun Picture.asUiModel() =
    PictureUiModel(
        id = id,
        author = author,
        imageUrl = downloadUrl,
        height = height,
        width = width,
        link = url
    )