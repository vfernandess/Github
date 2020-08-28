package com.voidx.search.data.repository

import com.voidx.repo.data.model.Repo
import com.voidx.search.data.model.SearchResult
import io.reactivex.rxjava3.core.Single

interface SearchRepository {

    fun searchRepo(searchOption: String, sortBy: String, page: Int): Single<SearchResult<Repo>>
}