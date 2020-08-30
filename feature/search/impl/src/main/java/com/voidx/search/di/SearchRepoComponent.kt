package com.voidx.search.di

import androidx.navigation.NavController
import com.voidx.github.utility.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
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
    fun navigation(): NavController
}