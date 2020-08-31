package com.voidx.pull.repo

import com.voidx.pull.data.model.PullRequest
import com.voidx.testing.unit.utility.factory.MockObjectFactory.getObjects
import io.reactivex.rxjava3.core.Single

fun mockSuccessRepoPullRequests(): Single<List<PullRequest>> {
    val results: List<PullRequest> = getObjects("pull_requests_200.json")
    return Single.just(results)
}

fun mockEmptyPullRequests(): Single<List<PullRequest>> {
    return Single.just(emptyList())
}

fun mockErrorPullRequests(): Single<List<PullRequest>> {
    return Single.error(RuntimeException())
}