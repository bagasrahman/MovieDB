package com.example.moviedb.fragment.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviedb.R
import com.example.moviedb.databinding.LayoutDetailFragmentBinding
import com.example.moviedb.view_model.DetailViewModel
import com.example.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment: BaseFragment<DetailViewModel, LayoutDetailFragmentBinding>() {
    override val vm: DetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_detail_fragment
    val detailArgs: DetailFragmentArgs by navArgs()
    val adapter =  DetailAdapter()


    override fun initBinding(binding: LayoutDetailFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
        vm.getDetailMovie(detailArgs.movieId)

        binding.rvReview.adapter = adapter

        vm.pagingReviewData.observe(this){
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitData(it)
            }
        }
    }
}