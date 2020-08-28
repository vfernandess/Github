package com.voidx.search.data.repository.impl.remote

import com.voidx.repo.data.model.Repo
import com.voidx.search.data.api.SearchAPI
import com.voidx.search.data.model.SearchResult
import com.voidx.search.data.repository.SearchRepository
import io.reactivex.rxjava3.core.Single

class SearchRemoteRepositoryImpl(private val searchAPI: SearchAPI): SearchRepository {

    override fun searchRepo(searchOption: String, sortBy: String, page: Int): Single<SearchResult<Repo>> {
        return searchAPI.searchRepo(searchOption, sortBy, page).map {
            it.body()?.apply {
                hasNextPage = it.headers()["link"]?.contains("next") ?: false
            }
        }
    }
}