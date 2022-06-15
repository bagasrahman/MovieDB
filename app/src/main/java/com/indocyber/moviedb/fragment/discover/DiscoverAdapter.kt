package com.indocyber.moviedb.fragment.discover

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indocyber.entity.movie.discover.Result
import com.example.moviedb.databinding.LayoutDiscoverItemBinding

class DiscoverAdapter(
    val navigateToDetail: (Int)-> Unit,
): PagingDataAdapter<Result, DiscoverViewHolder>(differ) {
    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val dataMovie = getItem(position)
        holder.binding.data = dataMovie
        Glide.with(holder.binding.root).load(
            "https://image.tmdb.org/t/p/w500${dataMovie?.backdropPath}"
        ).into(holder.binding.movieBackdrop)
        holder.binding.root.setOnClickListener {
            dataMovie?.let {
                it -> navigateToDetail(it.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        return DiscoverViewHolder(
            LayoutDiscoverItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class DiscoverViewHolder(
    val binding: LayoutDiscoverItemBinding
):RecyclerView.ViewHolder(binding.root)