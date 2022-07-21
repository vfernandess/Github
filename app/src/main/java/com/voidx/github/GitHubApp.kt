package com.voidx.github

import android.app.Application
import com.voidx.github.core.di.ext.DependenciesProvider
import com.voidx.github.core.navigator.Navigator
import com.voidx.github.di.ApplicationComponent
import com.voidx.github.di.DaggerApplicationComponent
import com.voidx.github.di.NavigationModule
import com.voidx.security.Keys

class GitHubApp : Application(), DependenciesProvider<Any> {

    lateinit var appComponent: ApplicationComponent

    override fun dependencies() = appComponent

    override fun onCreate() {
        super.onCreate()

        val key = Keys.apiKey()
        print(key)
    }

    fun createApplicationComponent(navigator: Navigator) {
        appComponent = DaggerApplicationComponent
            .builder()
            .navigationModule(NavigationModule(navigator, this))
            .build()
    }
}
