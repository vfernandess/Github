package com.voidx.search.data.repository.impl

import com.voidx.repo.data.model.Repo
import com.voidx.search.data.model.SearchResult
import com.voidx.search.data.repository.SearchRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteRepository: SearchRepository
): SearchRepository {

    override fun searchRepo(searchOption: String, sortBy: String, page: Int): Single<SearchResult<Repo>> {
        return remoteRepository.searchRepo(searchOption, sortBy, page)
    }
}