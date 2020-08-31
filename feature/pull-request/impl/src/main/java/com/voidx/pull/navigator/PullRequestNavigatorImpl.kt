package com.voidx.pull.navigator

import androidx.navigation.NavController
import com.voidx.repo.model.RepoDTO
import javax.inject.Inject

class PullRequestNavigatorImpl @Inject constructor(
    private val navigation: NavController
) : PullRequestNavigator {

    override fun showPullRequest(repo: RepoDTO) {

    }
}