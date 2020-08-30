package com.voidx.search.di

import com.voidx.search.navigation.SearchNavigator
import com.voidx.search.navigation.SearchNavigatorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface SearchExposedModule {

    @[Binds Singleton]
    fun providesSearchNavigator(navigation: SearchNavigatorImpl): SearchNavigator
}