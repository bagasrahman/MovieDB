package com.indocyber.moviedb.module

import android.content.Context
import com.indocyber.api_service.retrofit_client.RetrofitClient
import com.indocyber.api_service.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit) = retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideDiscoverService(retrofit: Retrofit) = retrofit.create(DiscoverService::class.java)

    @Provides
    @Singleton
    fun provideDetailService(retrofit: Retrofit) = retrofit.create(DetailService::class.java)

    @Provides
    @Singleton
    fun provideReviewService(retrofit: Retrofit) = retrofit.create(ReviewService::class.java)

    @Provides
    @Singleton
    fun provideTrailerService(retrofit: Retrofit) = retrofit.create(TrailerService::class.java)
}