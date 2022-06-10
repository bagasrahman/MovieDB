package com.example.api_service.service

import com.example.common.Constants
import com.example.entity.movie.reviews.MovieReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int?
    ): Response<MovieReviewsResponse>
}