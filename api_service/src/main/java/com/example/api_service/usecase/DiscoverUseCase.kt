package com.example.api_service.usecase

import com.example.api_service.paging.DiscoverMoviePager
import com.example.api_service.service.DiscoverService

class DiscoverUseCase(private val discoverService: DiscoverService) {
    operator fun invoke(genres: String) =
        DiscoverMoviePager.createPager(10,discoverService, genres).flow
}