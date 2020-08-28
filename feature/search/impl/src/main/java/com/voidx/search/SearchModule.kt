package com.voidx.search

import androidx.navigation.NavController
import com.voidx.github.utility.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import com.voidx.search.navigation.SearchNavigator
import com.voidx.search.navigation.SearchNavigatorImpl
import com.voidx.search.data.api.SearchAPI
import com.voidx.search.data.repository.SearchRepository
import com.voidx.search.data.repository.impl.SearchRepositoryImpl
import com.voidx.search.data.repository.impl.remote.SearchRemoteRepositoryImpl
import com.voidx.search.repo.domain.SearchRepoUseCase
import com.voidx.search.repo.domain.impl.SearchRepoUseCaseImpl

fun providesSearchNavigator(navigation: NavController): SearchNavigator {
    return SearchNavigatorImpl(navigation)
}

fun providesSearchRepository(api: SearchAPI): SearchRepository {
    val remote = SearchRemoteRepositoryImpl(api)
    return SearchRepositoryImpl(remote)
}

fun providesSearchRepoUseCase(
    searchRepository: SearchRepository,
    mapper: Mapper<Repo, RepoDTO>
): SearchRepoUseCase {
    return SearchRepoUseCaseImpl(searchRepository, mapper)
}