package com.indocyber.moviedb.fragment.genre

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviedb.R
import com.example.moviedb.databinding.LayoutGenreFragmentBinding
import com.indocyber.moviedb.view_model.GenreViewModel
import com.indocyber.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, LayoutGenreFragmentBinding>() {
    override val vm: GenreViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_genre_fragment
    val adapter = GenreAdapter(::startActionMode, {
        vm.selectedGenre.value.orEmpty()
    }) { toggleClick(it) }

    override fun initBinding(binding: LayoutGenreFragmentBinding) {
        super.initBinding(binding)
        binding.rvGenre.adapter = adapter

        observeLiveData()

        binding.nextButton.setOnClickListener {
            findNavController().navigate(GenreFragmentDirections.toDiscoverFragment(
                vm.selectedGenre.value.orEmpty().map { it.id }.joinToString(",")
            ))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        actionMode = null
    }
}