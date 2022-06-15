package com.indocyber.api_service.service

import com.indocyber.common.Constants
import com.indocyber.entity.movie.trailer.TrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrailerService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String =Constants.API_KEY
    ): Response<TrailerResponse>
}