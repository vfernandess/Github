package com.voidx.pull.repo.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.voidx.github.core.di.ext.dependencies
import com.voidx.github.core.navigator.Destination
import com.voidx.github.core.presentation.State
import com.voidx.pull.impl.databinding.FragmentRepoPullRequestBinding
import com.voidx.pull.di.DaggerPullRequestComponent
import com.voidx.pull.navigator.PullRequestNavigator
import com.voidx.pull.repo.presentation.RepoPullRequestViewModel
import com.voidx.repo.model.RepoDTO
import javax.inject.Inject

internal class RepoPullRequestFragment : Fragment(), Destination {

    companion object {

        private const val REPO_EXTRA = "REPO_EXTRA"

        @JvmStatic
        fun newInstance(repo: RepoDTO): RepoPullRequestFragment {
            return RepoPullRequestFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(REPO_EXTRA, repo)
                }
            }
        }
    }

    @Inject
    lateinit var navigator: PullRequestNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: RepoPullRequestViewModel by viewModels { viewModelProviderFactory }

    @Inject
    lateinit var adapter: RepoPullRequestAdapter

    private lateinit var binding: FragmentRepoPullRequestBinding

    override fun onAttach(context: Context) {
        DaggerPullRequestComponent
            .factory()
            .create(dependencies())
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentRepoPullRequestBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.pullRequests.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onItemClick = {
            it?.url?.let { url ->
                navigator.openPullRequest(url)
            }
        }

        viewModel.state().observe({ lifecycle }) { state ->
            when (state) {
                State.Loading -> transitionToLoadingState()
                State.Empty -> transitionToEmptyState()
                State.Success -> transitionToSuccessState()
                State.Error -> transitionToErrorState()
            }
        }

        viewModel.prs().observe({ lifecycle }) { results ->
            adapter.setData(results)
        }

        arguments?.getParcelable<RepoDTO>(REPO_EXTRA)?.let {
            viewModel.load(it)
        }
    }

    override fun getTitle(): String? {
        return arguments?.getParcelable<RepoDTO>(REPO_EXTRA)?.name
    }

    private fun transitionToErrorState() {
        binding.pullRequests.visibility = View.GONE
        binding.empty.visibility = View.GONE
        binding.pullToRefresh.isRefreshing = false
    }

    private fun transitionToSuccessState() {
        binding.pullRequests.visibility = View.VISIBLE
        binding.empty.visibility = View.GONE
        binding.pullToRefresh.isRefreshing = false
    }

    private fun transitionToEmptyState() {
        binding.pullRequests.visibility = View.GONE
        binding.empty.visibility = View.VISIBLE
        binding.pullToRefresh.isRefreshing = false
    }

    private fun transitionToLoadingState() {
        binding.empty.visibility = View.GONE
        binding.pullToRefresh.isRefreshing = true
    }

}
