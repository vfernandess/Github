package com.voidx.search.repo.domain

import com.voidx.repo.model.RepoDTO
import io.reactivex.rxjava3.core.Single

interface SearchRepoUseCase {

    fun searchRepo(): Single<List<RepoDTO>>

    fun resetSearchPaging()

    fun isFirstPage(): Boolean

}