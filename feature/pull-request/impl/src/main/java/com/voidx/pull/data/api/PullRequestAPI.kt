package com.voidx.pull.data.api

import com.voidx.pull.data.model.PullRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestAPI {

    @GET("repos/{user}/{repo}/pulls")
    fun getPullRequestsFromRepo(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): Single<List<PullRequest>>

}