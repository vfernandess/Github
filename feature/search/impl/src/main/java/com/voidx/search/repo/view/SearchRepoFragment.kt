package com.voidx.search.repo.view

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
import com.voidx.github.core.presentation.State
import com.voidx.github.core.view.recyclerview.InfiniteLoadMore
import com.voidx.github.core.view.recyclerview.withBottomLoad
import com.voidx.search.databinding.FragmentSearchRepoBinding
import com.voidx.search.di.DaggerSearchRepoComponent
import com.voidx.search.navigation.SearchNavigator
import com.voidx.search.repo.presentation.SearchRepoViewModel
import javax.inject.Inject

class SearchRepoFragment : Fragment() {

    companion object {

        const val VISIBLE_ITEMS_FOR_LOADING_MORE = 5
    }

    @Inject
    lateinit var navigator: SearchNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: SearchRepoViewModel by viewModels { viewModelProviderFactory }

    @Inject
    lateinit var adapter: SearchRepoAdapter

    private lateinit var binding: FragmentSearchRepoBinding

    private val infiniteBottomLoad: InfiniteLoadMore by lazy {
        binding.results.withBottomLoad(VISIBLE_ITEMS_FOR_LOADING_MORE)
    }

    override fun onAttach(context: Context) {
        DaggerSearchRepoComponent
            .factory()
            .create(dependencies())
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchRepoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.results.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onItemClick = {
            it?.let(navigator::showPullRequestsFromRepo)
        }

        binding.pullToRefresh.setOnRefreshListener {
            adapter.reset()
            viewModel.reload()
        }

        infiniteBottomLoad.onLoadMore = viewModel::load

        viewModel.state().observe({ lifecycle }) { state ->
            when (state) {
                State.Loading -> transitionToLoadingState()
                State.Empty -> transitionToEmptyState()
                State.Success -> transitionToSuccessState()
                State.Error -> transitionToErrorState()
            }
        }

        viewModel.results().observe({ lifecycle }) { results ->
            adapter.setData(results)
        }

        viewModel.load()
    }

    private fun transitionToErrorState() {
        binding.results.visibility = GONE
        binding.empty.visibility = GONE
        binding.pullToRefresh.isRefreshing = false
        infiniteBottomLoad.isLoading = false
    }

    private fun transitionToSuccessState() {
        binding.results.visibility = VISIBLE
        binding.empty.visibility = GONE
        binding.pullToRefresh.isRefreshing = false
        infiniteBottomLoad.isLoading = false
    }

    private fun transitionToEmptyState() {
        binding.results.visibility = GONE
        binding.empty.visibility = VISIBLE
        binding.pullToRefresh.isRefreshing = false
        infiniteBottomLoad.isLoading = false
    }

    private fun transitionToLoadingState() {
        binding.empty.visibility = GONE
        binding.pullToRefresh.isRefreshing = true
        infiniteBottomLoad.isLoading = true
    }
}