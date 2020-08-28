package com.voidx.search.repo

import com.google.gson.reflect.TypeToken
import com.voidx.repo.data.model.Repo
import com.voidx.search.data.model.SearchResult
import com.voidx.testing.unit.utility.factory.MockObjectFactory.getObject
import io.reactivex.rxjava3.core.Single

fun mockSuccessfullyResultsWithNextPage(): Single<SearchResult<Repo>> {
    val type = object: TypeToken<SearchResult<Repo>>() {}.type
    val result: SearchResult<Repo> = getObject("search_repo_200.json", type)
    result.hasNextPage = true
    return Single.just(result)
}

fun mockSuccessfullyResultsWithoutNextPage(): Single<SearchResult<Repo>> {
    val result: SearchResult<Repo> = getObject("search_repo_200.json")
    result.hasNextPage = false
    return Single.just(result)
}

fun mockEmptyResults(): Single<SearchResult<Repo>> {
    return Single.just(SearchResult(emptyList(), false))
}