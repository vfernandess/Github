package com.voidx.github.di

import com.voidx.github.core.di.CoreAndroidExposedModule
import com.voidx.repo.impl.di.RepoExposedModule
import com.voidx.search.di.SearchExposedModule
import com.voidx.search.di.SearchRepoDependencies
import com.voidx.user.di.UserExposedModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NavigationModule::class,
        CoreAndroidExposedModule::class,
        UserExposedModule::class,
        RepoExposedModule::class,
        SearchExposedModule::class
    ]
)
@Singleton
interface ApplicationComponent : SearchRepoDependencies {

}