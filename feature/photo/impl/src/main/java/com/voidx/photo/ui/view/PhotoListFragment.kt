package com.voidx.photo.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.voidx.github.core.di.ext.dependencies
import com.voidx.github.core.view.recyclerview.InfiniteLoadMore
import com.voidx.github.core.view.recyclerview.withBottomLoad
import com.voidx.photo.di.DaggerPhotoListComponent
import com.voidx.photo.impl.databinding.PhotoListFragmentBinding
import com.voidx.photo.ui.presentation.PhotoListState
import com.voidx.photo.ui.presentation.PhotoListViewModel
import javax.inject.Inject

class PhotoListFragment: Fragment() {

    @Inject
    lateinit var adapter: PhotoListAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: PhotoListViewModel by viewModels { viewModelProviderFactory }

    private lateinit var binding: PhotoListFragmentBinding

    private val infiniteBottomLoad: InfiniteLoadMore by lazy {
        binding.list.withBottomLoad(visibleThreshold = 3)
    }

    override fun onAttach(context: Context) {
        DaggerPhotoListComponent.factory().create(dependencies()).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhotoListFragmentBinding.inflate(inflater, container, false)

        binding.list.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        infiniteBottomLoad.onLoadMore = {
            viewModel.load()
        }

        binding.retry.setOnClickListener {
            viewModel.load()
        }

        viewModel.state().observe({ lifecycle }) { state ->
            when (state) {
                PhotoListState.Empty -> showEmptyState()
                PhotoListState.Error -> showError()
                PhotoListState.Loading -> showLoading()
                is PhotoListState.ShowPhotoList -> showPhotoList(state)
            }
        }

        viewModel.load()
    }

    private fun showPhotoList(state: PhotoListState.ShowPhotoList) {
        binding.emptyState.visibility = GONE
        binding.retry.visibility = GONE

        adapter.addItems(state.photos)
        infiniteBottomLoad.isLoading = false
    }

    private fun showLoading() {
        binding.emptyState.visibility = GONE
        binding.retry.visibility = GONE
    }

    private fun showError() {
        infiniteBottomLoad.isLoading = false
        binding.emptyState.text = "Ooooops something happened"
        binding.retry.visibility = VISIBLE
    }

    private fun showEmptyState() {
        infiniteBottomLoad.isLoading = false
        binding.emptyState.text = "There is no photos at this time"
        binding.retry.visibility = GONE
    }
}
