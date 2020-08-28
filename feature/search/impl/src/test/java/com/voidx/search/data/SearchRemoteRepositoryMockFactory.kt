package com.voidx.search.data

import com.voidx.repo.data.model.Repo
import com.voidx.search.data.model.SearchResult
import com.voidx.testing.unit.utility.factory.MockObjectFactory.getObject
import io.reactivex.rxjava3.core.Single
import okhttp3.Headers
import retrofit2.Response

fun mockSearchRepoWithNextHeaders(): Single<Response<SearchResult<Repo>>> {
    return Single
        .just(
            Response
                .success(
                    getObject<SearchResult<Repo>>("search_repo_200.json"),
                    Headers.headersOf("link", "next")
                )
        )
}

fun mockSearchRepoWithoutNextHeaders(): Single<Response<SearchResult<Repo>>> {
    return Single.just(
        Response.success(
            getObject<SearchResult<Repo>>("search_repo_200.json")
        )
    )
}