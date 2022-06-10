package com.example.moviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.api_service.usecase.GenreUseCase
import com.example.common.AppResponse
import com.example.entity.genre.Genre
import com.example.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    application: Application,
    val genreUseCase: GenreUseCase
) : BaseViewModel(application) {
    val selectedGenre = MutableLiveData<List<Genre>>()
    val genreLiveData = MutableLiveData<AppResponse<List<Genre>>>()

    init {
        getGenrelist()
    }

    fun getGenrelist() {
        viewModelScope.launch {
            genreUseCase().collect {
                genreLiveData.postValue(it)
            }
        }
    }
}