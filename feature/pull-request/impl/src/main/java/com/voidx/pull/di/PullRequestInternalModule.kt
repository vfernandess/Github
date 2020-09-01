package com.voidx.pull.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voidx.github.core.lifecycle.DefaultViewModelProviderFactory
import com.voidx.github.core.lifecycle.ViewModelKey
import com.voidx.pull.data.api.PullRequestAPI
import com.voidx.pull.data.repository.PullRequestRepository
import com.voidx.pull.data.repository.impl.PullRequestRemoteRepositoryImpl
import com.voidx.pull.data.repository.impl.PullRequestRepositoryImpl
import com.voidx.pull.repo.domain.RepoPullRequestUseCase
import com.voidx.pull.repo.domain.impl.RepoPullRequestUseCaseImpl
import com.voidx.pull.repo.presentation.RepoPullRequestViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(
    includes = [
        PullRequestDataModule::class,
        PullRequestDomainModule::class,
        PullRequestViewModelModule::class,
        PullRequestViewModule::class
    ]
)
internal interface PullRequestInternalModule

@Module
internal interface PullRequestDataModule {

    companion object {

        @[Provides]
        fun providesSearchRepository(api: PullRequestAPI): PullRequestRepository {
            val remote = PullRequestRemoteRepositoryImpl(api)
            return PullRequestRepositoryImpl(remote)
        }

        @[Provides]
        fun providesPullRequestApi(retrofit: Retrofit): PullRequestAPI =
            retrofit.create(PullRequestAPI::class.java)
    }
}

@Module
internal interface PullRequestDomainModule {

    @[Binds]
    fun bindsRepoPullRequestUseCase(getMovies: RepoPullRequestUseCaseImpl): RepoPullRequestUseCase
}

@Module
internal interface PullRequestViewModelModule {

    @[Binds IntoMap ViewModelKey(RepoPullRequestViewModel::class)]
    fun bindsMoviesViewModel(moviesViewModel: RepoPullRequestViewModel): ViewModel

    @[Binds]
    fun bindsDefaultViewModelProviderFactory(
        viewModelProviderFactory: DefaultViewModelProviderFactory
    ): ViewModelProvider.Factory
}

@Module
internal interface PullRequestViewModule