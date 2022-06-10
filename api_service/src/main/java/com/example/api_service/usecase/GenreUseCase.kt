package com.example.api_service.usecase

import com.example.api_service.service.GenreService
import com.example.common.AppResponse
import com.example.entity.genre.Genre
import kotlinx.coroutines.flow.flow


class GenreUseCase(private val genreService: GenreService) {
    operator fun invoke() = flow {
        emit(AppResponse.loading())
        try {
            val data = genreService.getGenres()
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it.genres))
                } ?: run {
                        emit(AppResponse.errorBackend<List<Genre>>(data.code(), data.errorBody()))
                    }
            } else {
                emit(AppResponse.errorBackend(data.code(), data.errorBody()))
            }
        } catch (e: Exception) {
            emit(AppResponse.errorSystem(e))
        }
    }
}