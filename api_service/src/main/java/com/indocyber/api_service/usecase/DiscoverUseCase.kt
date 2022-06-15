package com.indocyber.api_service.usecase

import com.indocyber.api_service.paging.DiscoverMoviePager
import com.indocyber.api_service.service.DiscoverService

class DiscoverUseCase(private val discoverService: DiscoverService) {
    operator fun invoke(genres: String) =
        DiscoverMoviePager.createPager(10,discoverService, genres).flow
}