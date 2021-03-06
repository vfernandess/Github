package com.voidx.search.di

import com.voidx.github.core.navigator.Navigator
import com.voidx.core.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import com.voidx.search.navigation.SearchNavigator
import com.voidx.search.repo.view.SearchRepoFragment
import dagger.Component
import io.reactivex.rxjava3.core.Scheduler
import retrofit2.Retrofit

@Component(
    modules = [SearchRepoInternalModule::class],
    dependencies = [SearchRepoDependencies::class]
)
internal interface SearchRepoComponent {

    fun inject(fragment: SearchRepoFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchRepoDependencies): SearchRepoComponent
    }
}

interface SearchRepoDependencies {
    fun retrofit(): Retrofit
    fun repoToRepoDtoMapper(): Mapper<Repo, RepoDTO>
    fun androidScheduler(): Scheduler
    fun navigation(): Navigator
    fun searchNavigator(): SearchNavigator
}