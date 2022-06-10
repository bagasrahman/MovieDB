package com.example.api_service.service

import com.example.common.Constants
import com.example.entity.movie.discover.DiscoverMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {
    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("with_genre") genres: String,
        @Query("page") page: Int?,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<DiscoverMovieResponse>
}