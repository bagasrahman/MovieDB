package com.indocyber.moviedb.module

import com.indocyber.api_service.service.*
import com.indocyber.api_service.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGenreUseCase(genreService: GenreService) = GenreUseCase(genreService)

    @Provides
    fun provideDiscoverUseCase(discoverService: DiscoverService) = DiscoverUseCase(discoverService)

    @Provides
    fun provideDetailUseCase(detailService: DetailService) = DetailUseCase(detailService)

    @Provides
    fun provideReviewUseCase(reviewService: ReviewService) = ReviewUseCase(reviewService)

    @Provides
    fun provideTrailerUseCase(trailerService: TrailerService) = TrailerUseCase(trailerService)
}