package com.example.moviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.api_service.usecase.DetailUseCase
import com.example.api_service.usecase.ReviewUseCase
import com.example.api_service.usecase.TrailerUseCase
import com.example.common.AppResponse
import com.example.entity.movie.details.MovieDetailsResponse
import com.example.entity.movie.reviews.Review
import com.example.entity.movie.trailer.TrailerResponse
import com.example.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    val detailUseCase: DetailUseCase,
    val reviewUseCase: ReviewUseCase,
    val trailerUseCase: TrailerUseCase
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
            reviewUseCase.invoke(movieId).collect {
                pagingReviewData.postValue(it)
            }
        }
        viewModelScope.launch {
            trailerUseCase.invoke(movieId).collect{
                trailerLiveData.postValue(it)
            }
        }
    }
}