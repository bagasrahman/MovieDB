package com.indocyber.moviedb.fragment.detail

import android.view.View
import com.bumptech.glide.Glide
import com.indocyber.common.ResponseSuccess
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
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
            is ResponseSuccess -> {
                it.data?.results?.get(0)?.let { it1 ->
                    videoTrailer(it1.key)
                }
            }
        }
    }

    vm.pagingReviewData.observe(this@observeLiveData) {
        CoroutineScope(Dispatchers.IO).launch {
            adapter.submitData(it)
        }
    }


}

fun DetailFragment.videoTrailer(videoId: String) {
    val listener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
            super.onReady(youTubePlayer)
            youTubePlayer.cueVideo(videoId, 0f)
            val defaultPlayerUiController =
                DefaultPlayerUiController(binding.videoTrailer, youTubePlayer)
            binding.videoTrailer.setCustomPlayerUi(defaultPlayerUiController.rootView)
        }
    }

    val option = IFramePlayerOptions.Builder().controls(0).build()
    binding.videoTrailer.initialize(listener, option)
}