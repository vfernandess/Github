package com.voidx.search.repo.domain.impl

import com.voidx.github.utility.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import com.voidx.search.data.model.SearchResult
import com.voidx.search.data.repository.SearchRepository
import com.voidx.search.repo.domain.SearchRepoUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchRepoUseCaseImpl @Inject constructor (
    private val repository: SearchRepository,
    private val mapper: Mapper<@JvmSuppressWildcards Repo, @JvmSuppressWildcards RepoDTO>
) : SearchRepoUseCase {

    private var page = 1
    private var hasNextPage = true

    override fun searchRepo(): Single<List<RepoDTO>> {
        if (hasNextPage.not()) {
            return Single.just(emptyList())
        }

        return repository
            .searchRepo("language:Kotlin", "stars", page)
            .map {
                handleResult(it)
            }
    }

    private fun handleResult(result: SearchResult<Repo>): List<RepoDTO> {
        hasNextPage = result.hasNextPage

        if (hasNextPage) {
            page += 1
        }

        return result.items.map { mapper.map(it) }
    }

    override fun resetSearchPaging() {
        page = 1
    }

    override fun isFirstPage(): Boolean =
        if (page == 1) {
            true
        } else {
            (page - 1) == 1
        }

}