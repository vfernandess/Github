package com.voidx.search.navigation

import com.voidx.github.core.navigator.Navigator
import com.voidx.pull.navigator.PullRequestNavigator
import com.voidx.repo.model.RepoDTO
import com.voidx.search.repo.view.SearchRepoFragment
import javax.inject.Inject

class SearchNavigatorImpl @Inject constructor (
    private val navigation: Navigator,
    private val pullRequestNavigator: PullRequestNavigator
) : SearchNavigator {

    override fun showRepoSearch() {
        navigation.navigateTo(SearchRepoFragment())
    }

    override fun showPullRequestsFromRepo(repo: RepoDTO) {
        pullRequestNavigator.showPullRequest(repo)
    }
}