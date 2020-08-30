package com.voidx.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voidx.github.core.lifecycle.DefaultViewModelProviderFactory
import com.voidx.github.core.lifecycle.ViewModelKey
import com.voidx.search.data.api.SearchAPI
import com.voidx.search.data.repository.SearchRepository
import com.voidx.search.data.repository.impl.SearchRepositoryImpl
import com.voidx.search.data.repository.impl.remote.SearchRemoteRepositoryImpl
import com.voidx.search.repo.domain.SearchRepoUseCase
import com.voidx.search.repo.domain.impl.SearchRepoUseCaseImpl
import com.voidx.search.repo.presentation.SearchRepoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(
    includes = [
        SearchRepoDataModule::class,
        SearchRepoDomainModule::class,
        SearchRepoViewModelModule::class,
        SearchRepoViewModule::class
    ]
)
internal interface SearchRepoInternalModule

@Module
internal interface SearchRepoDataModule {

//    @[Binds]
//    fun bindsSearchRemoteRepository(searchRepository: SearchRemoteRepositoryImpl): SearchRemoteRepositoryImpl
//
//    @[Binds]
//    fun bindsSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository

    companion object {

        @[Provides]
        fun providesSearchRepository(api: SearchAPI): SearchRepository {
            val remote = SearchRemoteRepositoryImpl(api)
            return SearchRepositoryImpl(remote)
        }

        @[Provides]
        fun providesApi(retrofit: Retrofit): SearchAPI =
            retrofit.create(SearchAPI::class.java)
    }
}

@Module
internal interface SearchRepoDomainModule {

    @[Binds]
    fun bindsSearchRepoUseCase(getMovies: SearchRepoUseCaseImpl): SearchRepoUseCase
}

@Module
internal interface SearchRepoViewModelModule {

    @[Binds IntoMap ViewModelKey(SearchRepoViewModel::class)]
    fun bindsMoviesViewModel(moviesViewModel: SearchRepoViewModel): ViewModel

    @[Binds]
    fun bindsDefaultViewModelProviderFactory(
        viewModelProviderFactory: DefaultViewModelProviderFactory
    ): ViewModelProvider.Factory
}

@Module
internal interface SearchRepoViewModule