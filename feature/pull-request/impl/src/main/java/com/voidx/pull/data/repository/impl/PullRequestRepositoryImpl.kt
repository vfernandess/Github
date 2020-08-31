package com.voidx.pull.data.repository.impl

import com.voidx.pull.data.model.PullRequest
import com.voidx.pull.data.repository.PullRequestRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PullRequestRepositoryImpl @Inject constructor (
    private val remote: PullRequestRepository
) : PullRequestRepository {

    override fun getPullRequestsFromRepo(user: String, repo: String): Single<List<PullRequest>> =
        remote.getPullRequestsFromRepo(user, repo)
}