package com.example.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.DiscoverService
import com.example.entity.movie.discover.Result

class DiscoverMovieDataSource(
    private val discoverService: DiscoverService,
    private val genres: String
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val result = discoverService.getMovieByGenre(
                genres, page = params.key ?: 1
            )
            result.body()?.let {
                LoadResult.Page(
                    data = it.results,
                    if (it.page == 1) null else it.page - 1,
                    it.page + 1
                )
            } ?: LoadResult.Error(Exception("invalid data"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

object DiscoverMoviePager {
    fun createPager(
        pageSize: Int,
        discoverService: DiscoverService,
        genres: String
    ): Pager<Int, Result> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            DiscoverMovieDataSource(discoverService,genres)
        }
    )
}