package com.example.moviedb.fragment.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entity.movie.reviews.Review
import com.example.moviedb.databinding.LayoutDetailFragmentBinding
import com.example.moviedb.databinding.LayoutDiscoverItemBinding
import com.example.moviedb.databinding.LayoutReviewItemBinding

class DetailAdapter(): PagingDataAdapter<Review, ReviewViewHolder>(differ) {
    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val dataMovie = getItem(position)
        holder.binding.data = dataMovie
        dataMovie?.authorDetails?.avatarPath?.let {
            if (it.substring(0, 5) == "/http") {
                Glide.with(holder.binding.root).load(it.substring(1))
                    .into(holder.binding.imgAvatar)
            }else{
                Glide.with(holder.binding.root).load(
                    "https://www.themoviedb.org/t/p/w300_and_h300_face${it}")
                    .circleCrop()
                    .into(holder.binding.imgAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutReviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<Review>(){
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class ReviewViewHolder(
    val binding: LayoutReviewItemBinding
): RecyclerView.ViewHolder(binding.root)