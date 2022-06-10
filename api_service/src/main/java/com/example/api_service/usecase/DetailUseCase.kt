package com.example.api_service.usecase

import com.example.api_service.service.DetailService
import com.example.common.AppResponse
import com.example.entity.movie.details.MovieDetailsResponse
import kotlinx.coroutines.flow.flow

class DetailUseCase(private val detailService: DetailService) {
    operator fun invoke(movieId: Int) = flow<AppResponse<MovieDetailsResponse>> {
        emit(AppResponse.loading())
        try {
            val data = detailService.getMovieDetail(movieId)
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it))
                }
                    ?: run {
                        emit(AppResponse.errorBackend(data.code(), data.errorBody()))
                    }
            } else {
                emit(AppResponse.errorBackend(data.code(), data.errorBody()))
            }
        } catch (e: Exception) {
            emit(AppResponse.errorSystem(e))
        }
    }
}