package com.voidx.search.navigation

import com.voidx.repo.model.RepoDTO

interface SearchNavigator {

    fun showRepoSearch()

    fun showPullRequestsFromRepo(repo: RepoDTO)
}