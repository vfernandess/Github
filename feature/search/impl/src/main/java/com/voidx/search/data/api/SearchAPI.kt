package com.voidx.search.data.api

import com.voidx.repo.data.model.Repo
import com.voidx.search.data.model.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {

    @GET("/search/repositories")
    fun searchRepo(
        @Query("q") searchOption: String,
        @Query("sort") sortBy: String,
        @Query("page") page: Int
    ): Single<Response<SearchResult<Repo>>>

}