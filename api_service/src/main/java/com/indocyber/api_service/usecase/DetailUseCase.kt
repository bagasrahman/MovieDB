package com.indocyber.api_service.usecase

import com.indocyber.api_service.service.DetailService
import com.indocyber.common.AppResponse
import com.indocyber.entity.movie.details.MovieDetailsResponse
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