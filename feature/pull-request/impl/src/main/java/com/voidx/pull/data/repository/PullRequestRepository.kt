package com.voidx.pull.data.repository

import com.voidx.pull.data.model.PullRequest
import io.reactivex.rxjava3.core.Single

interface PullRequestRepository {

    fun getPullRequestsFromRepo(user: String, repo: String): Single<List<PullRequest>>
}