package com.indocyber.api_service.service

import com.indocyber.common.Constants
import com.indocyber.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<GenreResponse>
}