package com.indocyber.moviedb.fragment.detail

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.indocyber.common.ResponseError
import com.indocyber.common.ResponseLoading
import com.indocyber.common.ResponseSuccess
import com.example.moviedb.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DetailFragment.observeLiveData() = with(vm) {

    observeResponseData(
        detailLiveData,
        success = {
            binding.retryButton.visibility = View.GONE
            binding.loading.visibility = View.GONE
            binding.dataMovieDetail = it
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${it?.posterPath}")
                .into(binding.poster)
        },
        loading = {
            binding.loading.visibility = View.VISIBLE
        },
        error = {
            binding.loading.visibility = View.GONE
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener {
                vm.getDetailMovie(detailArgs.movieId)
            }
        })

    vm.trailerLiveData.observe(this@observeLiveData) {
        when (trailerLiveData.value) {
            is com.indocyber.common.ResponseSuccess -> {
                it.data?.results?.get(0)?.let { it1 ->
                    videoTrailer(it1.key)
                }
            }
        }
    }

    vm.pagingReviewData.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            adapter.submitData(it)
        }
    }



}

fun DetailFragment.videoTrailer(videoId: String) {
    val youtubeFragment = YouTubePlayerSupportFragmentX.newInstance()
    with(parentFragmentManager) {
        beginTransaction().apply {
            add(R.id.videoTrailer, youtubeFragment)
            commit()
        }
    }
    youtubeFragment.initialize("AIzaSyCF-ZPzLCRzKPNrTG1v6FhsB-xCabbJCOI",
        object : YouTubePlayerSupportFragmentX.OnInitializedListener() {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.cueVideo(videoId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.e("youtubePlayer", "error ${p1?.name}")
            }
        }
    )
}