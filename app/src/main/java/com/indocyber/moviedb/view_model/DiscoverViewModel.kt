package com.indocyber.moviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.indocyber.api_service.usecase.DiscoverUseCase
import com.indocyber.entity.movie.discover.Result
import com.indocyber.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    application: Application,
    val discoverUseCase: DiscoverUseCase
): BaseViewModel(application) {
    val pagingDiscoverData =MutableLiveData<PagingData<Result>>()

    fun getDiscoverByGenre(genreIds: String){
        viewModelScope.launch {
            discoverUseCase.invoke(genreIds).collect {
                pagingDiscoverData.postValue(it)
            }
        }
    }
}