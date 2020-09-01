package com.voidx.pull.di

import com.voidx.github.core.navigator.Navigator
import com.voidx.core.data.Mapper
import com.voidx.pull.data.model.PullRequest
import com.voidx.pull.navigator.PullRequestNavigator
import com.voidx.pull.repo.domain.model.PullRequestDTO
import com.voidx.pull.repo.view.RepoPullRequestFragment
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import dagger.Component
import io.reactivex.rxjava3.core.Scheduler
import retrofit2.Retrofit

@Component(
    modules = [PullRequestInternalModule::class],
    dependencies = [RepoPullRequestDependencies::class]
)
internal interface PullRequestComponent {

    fun inject(fragment: RepoPullRequestFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: RepoPullRequestDependencies): PullRequestComponent
    }
}

interface RepoPullRequestDependencies {
    fun retrofit(): Retrofit
    fun repoToRepoDtoMapper(): Mapper<Repo, RepoDTO>
    fun prToPrDtoMapper(): Mapper<PullRequest, PullRequestDTO>
    fun androidScheduler(): Scheduler
    fun navigation(): Navigator
    fun pullRequestNavigator(): PullRequestNavigator
}