package com.voidx.pull.navigator

import com.voidx.repo.model.RepoDTO

interface PullRequestNavigator {

    fun showPullRequest(repo: RepoDTO)

    fun openPullRequest(url: String)
}