package com.example.moviedb.fragment.genre

import android.view.View
import androidx.appcompat.view.ActionMode
import com.example.entity.genre.Genre
import com.example.langext.toggle


var actionMode: ActionMode? = null

fun GenreFragment.observeLiveData() = with(vm) {

    observeResponseData(
        genreLiveData,
        success = {
            adapter.submitData(it)
            binding.loadingContainer.visibility = View.GONE
            binding.retryButton.visibility = View.GONE
        },
        error = {
            binding.loadingContainer.visibility = View.GONE
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener {
                vm.getGenrelist()
            }
        },
        loading = {
            binding.loadingContainer.visibility = View.VISIBLE
        }

    )
    genreLiveData.observe(this@observeLiveData) {
        it.data?.let { it1 ->
            adapter.submitData(it1)
        }
    }

    selectedGenre.observe(this@observeLiveData) {
        if (it.isEmpty()) {
            binding.nextButton.visibility = View.GONE
        } else {
            binding.nextButton.visibility = View.VISIBLE
        }
    }
}

fun GenreFragment.toggleClick(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selectedGenre.toggle(genre)
    }

}

fun GenreFragment.startActionMode(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selectedGenre.toggle(genre)
    }
}

