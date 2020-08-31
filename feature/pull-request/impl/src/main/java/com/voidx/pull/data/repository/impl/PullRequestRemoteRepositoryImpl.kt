package com.voidx.pull.data.repository.impl

import com.voidx.pull.data.api.PullRequestAPI
import com.voidx.pull.data.model.PullRequest
import com.voidx.pull.data.repository.PullRequestRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PullRequestRemoteRepositoryImpl @Inject constructor(
    private val api: PullRequestAPI
) : PullRequestRepository {

    override fun getPullRequestsFromRepo(user: String, repo: String): Single<List<PullRequest>> =
        api.getPullRequestsFromRepo(user, repo)
}