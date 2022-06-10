package com.example.api_service.usecase

import com.example.api_service.service.TrailerService
import com.example.common.AppResponse
import com.example.entity.movie.trailer.TrailerResponse
import kotlinx.coroutines.flow.flow

class TrailerUseCase(private val trailerService: TrailerService) {
    operator fun invoke(movieId: Int) = flow<AppResponse<TrailerResponse>> {
        emit(AppResponse.loading())
        try {
            val data = trailerService.getMovieTrailer(movieId)
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