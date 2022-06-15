package com.indocyber.moviedb.fragment.discover

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.databinding.LayoutDiscoverFragmentBinding


class DiscoverMovieStateAdapter() : LoadStateAdapter<DiscoverStateViewHolder>() {
    override fun onBindViewHolder(holder: DiscoverStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DiscoverStateViewHolder =
        DiscoverStateViewHolder(
            LayoutDiscoverFragmentBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
}

class DiscoverStateViewHolder(val binding: LayoutDiscoverFragmentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                binding.retryButton.visibility = View.VISIBLE
                binding.loadingContainer.visibility = View.GONE
            }
            is LoadState.Loading -> {
                binding.loadingContainer.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
            }
            is LoadState.NotLoading -> {
                binding.loadingContainer.visibility = View.GONE
                binding.retryButton.visibility = View.GONE
            }
        }
    }
}