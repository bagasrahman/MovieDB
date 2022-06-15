package com.indocyber.moviedb.fragment.genre

import android.R
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.databinding.LayoutGenreItemBinding
import com.indocyber.entity.genre.Genre


class GenreAdapter(
    val startSupportActionClick: (Genre) -> Unit,
    val getSelection: () -> List<Genre>,
    val toggleClick: (Genre) -> Unit
) : RecyclerView.Adapter<GenreViewHolder>() {
    private val differ = AsyncListDiffer<Genre>(this, itemCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutGenreItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.data = data
        holder.binding.isSelected = getSelection().contains(data)
        holder.binding.root.setOnClickListener {
            startSupportActionClick(data)
            true
        }
        holder.binding.root.setOnClickListener {
            toggleClick(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitData(data: List<Genre>){
        differ.submitList(data)
    }

    fun clearSelection(changes: (() -> Unit)?) {
        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = differ.currentList.size

            override fun getNewListSize(): Int = differ.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return !getSelection().contains(differ.currentList[oldItemPosition])
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes?.invoke()
        differ.dispatchUpdatesTo(this)
    }

    fun toggleSelection(genre: Genre, changes: () -> Unit) {
        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = differ.currentList.size

            override fun getNewListSize(): Int = differ.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return differ.currentList[oldItemPosition] != genre
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes()
        differ.dispatchUpdatesTo(this)
    }


    companion object {
        val itemCallBack = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return true
            }
        }
    }
}

class GenreViewHolder(
    val binding: LayoutGenreItemBinding
) : RecyclerView.ViewHolder(binding.root) {}

