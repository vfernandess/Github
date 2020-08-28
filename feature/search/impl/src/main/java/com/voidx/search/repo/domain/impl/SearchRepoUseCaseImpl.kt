package com.voidx.search.repo.domain.impl

import com.voidx.github.utility.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import com.voidx.search.data.model.SearchResult
import com.voidx.search.data.repository.SearchRepository
import com.voidx.search.repo.domain.SearchRepoUseCase
import io.reactivex.rxjava3.core.Single

class SearchRepoUseCaseImpl(
    private val repository: SearchRepository,
    private val mapper: Mapper<Repo, RepoDTO>
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

        page += 1
        return result.items.map { mapper.map(it) }
    }

    override fun resetSearchPaging() {
        page = 1
    }

    override fun isFirstPage(): Boolean = page == 1

}