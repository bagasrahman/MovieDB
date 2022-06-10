package com.example.moviedb.fragment.discover

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.moviedb.R
import com.example.moviedb.databinding.LayoutDiscoverFragmentBinding
import com.example.moviedb.databinding.LayoutDiscoverItemBinding
import com.example.moviedb.view_model.DiscoverViewModel
import com.example.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<DiscoverViewModel, LayoutDiscoverFragmentBinding>() {
    override val vm: DiscoverViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_discover_fragment
    val adapter = DiscoverAdapter() {
        findNavController().navigate(DiscoverFragmentDirections.toDetailFragment(it))
    }
    val discoverArgs: DiscoverFragmentArgs by navArgs()
    val loadStateAdapter = DiscoverMovieStateAdapter()

    override fun initBinding(binding: LayoutDiscoverFragmentBinding) {
        super.initBinding(binding)
        binding.rvDiscover.adapter = adapter.withLoadStateFooter(loadStateAdapter)

        vm.getDiscoverByGenre(discoverArgs.genreId)

        vm.pagingDiscoverData.observe(this) {
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitData(it)
            }
        }

        adapter.addLoadStateListener {
            if (it.append is LoadState.Error || it.refresh is LoadState.Error ||
                it.prepend is LoadState.Error
            ) {
                binding.rvDiscover.visibility = View.GONE
                binding.retryButton.visibility = View.VISIBLE
                binding.loadingContainer.visibility = View.GONE
                binding.retryButton.setOnClickListener {
                    vm.getDiscoverByGenre(discoverArgs.genreId)
                }
            } else if (it.refresh is LoadState.Loading) {
                binding.loadingContainer.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
                binding.rvDiscover.visibility = View.GONE
            } else {
                binding.rvDiscover.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
                binding.loadingContainer.visibility = View.GONE
            }
        }


    }
}
