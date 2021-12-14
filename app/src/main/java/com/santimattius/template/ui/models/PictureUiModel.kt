package com.santimattius.template.ui.models

data class PictureUiModel(
    val id: String,
    val author: String,
    val imageUrl: String,
    val height: Int,
    val width: Int,
    val link: String
) : UiItem(id)