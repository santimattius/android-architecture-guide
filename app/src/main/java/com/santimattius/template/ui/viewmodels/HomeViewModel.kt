package com.santimattius.template.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.template.domain.usecases.GetPictures
import com.santimattius.template.ui.models.Data
import com.santimattius.template.ui.models.Error
import com.santimattius.template.ui.models.HomeState
import com.santimattius.template.ui.models.Loading
import com.santimattius.template.ui.models.mapping.asUiModels
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel(private val getPictures: GetPictures) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState>
        get() = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.postValue(Error)
    }

    init {
        pictures()
    }

    private fun pictures() {
        viewModelScope.launch(exceptionHandler) {
            _state.postValue(Loading)
            val pictures = getPictures().asUiModels()
            _state.postValue(Data(values = pictures))
        }
    }

    fun retry() {
        pictures()
    }
}

