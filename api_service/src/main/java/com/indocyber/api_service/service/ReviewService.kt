package com.indocyber.api_service.service

import com.indocyber.common.Constants
import com.indocyber.entity.movie.reviews.MovieReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieReviewsResponse>
}