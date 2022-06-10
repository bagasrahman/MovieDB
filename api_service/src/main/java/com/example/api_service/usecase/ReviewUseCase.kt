package com.example.api_service.usecase

import com.example.api_service.paging.ReviewMoviePager
import com.example.api_service.service.ReviewService

class ReviewUseCase(private val reviewService: ReviewService) {
    operator fun invoke(movieId: Int)=
        ReviewMoviePager.createPager(10, reviewService, movieId).flow
}