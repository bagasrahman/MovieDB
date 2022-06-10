package com.example.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.ReviewService
import com.example.entity.movie.reviews.Review

class MovieReviewDataSource(
    private val reviewService: ReviewService,
    private val movieId: Int
): PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
      return try {
          val result = reviewService.getMovieReview(movieId, page = params.key?:1)
          result.body()?.let {
              LoadResult.Page(
                  data = it.results,
                  if(it.page == 1) null else it.page -1,
                  it.page + 1
              )
          }?: LoadResult.Error(java.lang.Exception("invalid data"))
      }catch (e:Exception){
          LoadResult.Error(e)
      }
    }
}

object ReviewMoviePager{
    fun createPager(
        pageSize:Int,
        reviewService: ReviewService,
        movieId:Int
    ): Pager<Int, Review> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            MovieReviewDataSource(reviewService,movieId)
        }
    )
}