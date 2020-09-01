package com.voidx.github.di

import android.content.Context
import com.voidx.github.core.navigator.Navigator
import dagger.Module
import dagger.Provides

@Module
class NavigationModule(private val navigator: Navigator, private val context: Context) {

    @Provides
    fun activityContent(): Context {
        return context
    }

    @Provides
    fun providesNavigation(): Navigator {
        return navigator
    }
}