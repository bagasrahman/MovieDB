package com.indocyber.moviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.indocyber.api_service.usecase.DetailUseCase
import com.indocyber.api_service.usecase.ReviewUseCase
import com.indocyber.api_service.usecase.TrailerUseCase
import com.indocyber.common.AppResponse
import com.indocyber.entity.movie.details.MovieDetailsResponse
import com.indocyber.entity.movie.reviews.Review
import com.indocyber.entity.movie.trailer.TrailerResponse
import com.indocyber.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val detailUseCase: DetailUseCase,
    private val reviewUseCase: ReviewUseCase,
    private val trailerUseCase: TrailerUseCase
) : BaseViewModel(application) {
    val detailLiveData = MutableLiveData<AppResponse<MovieDetailsResponse>>()
    val trailerLiveData = MutableLiveData<AppResponse<TrailerResponse>>()
    val pagingReviewData = MutableLiveData<PagingData<Review>>()

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            detailUseCase(movieId).collect {
                detailLiveData.postValue(it)
            }
        }
        viewModelScope.launch {
            trailerUseCase.invoke(movieId).collect {
                trailerLiveData.postValue(it)
            }
        }
        viewModelScope.launch {
            reviewUseCase.invoke(movieId).collect {
                pagingReviewData.postValue(it)
            }
        }
    }
}