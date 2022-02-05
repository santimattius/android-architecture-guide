package com.santimattius.template.ui.models

data class HomeState(
    val isLoading: Boolean = false,
    val withError: Boolean = false,
    val pictures: List<PictureUiModel> = emptyList()
)

val HomeState.isEmpty: Boolean get() = pictures.isEmpty()

fun initialState() = HomeState(isLoading = true)
