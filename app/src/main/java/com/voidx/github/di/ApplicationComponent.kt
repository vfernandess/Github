package com.voidx.github.di

import com.voidx.core.di.CoreLibExposedModule
import com.voidx.github.core.di.CoreAndroidExposedModule
import com.voidx.photo.di.PhotoListDependencies
import com.voidx.photo.di.PhotoListExposedModule
import com.voidx.pull.di.PullRequestExposedModule
import com.voidx.pull.di.RepoPullRequestDependencies
import com.voidx.repo.impl.di.RepoExposedModule
import com.voidx.search.di.SearchExposedModule
import com.voidx.search.di.SearchRepoDependencies
import com.voidx.user.di.UserExposedModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        EnvironmentModule::class,
        CoreLibExposedModule::class,
        CoreAndroidExposedModule::class,
        NavigationModule::class,
        UserExposedModule::class,
        RepoExposedModule::class,
        SearchExposedModule::class,
        PullRequestExposedModule::class,
        PhotoListExposedModule::class
    ]
)
@Singleton
interface ApplicationComponent :
    SearchRepoDependencies,
    RepoPullRequestDependencies,
    PhotoListDependencies
