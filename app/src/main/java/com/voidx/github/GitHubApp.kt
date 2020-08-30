package com.voidx.github

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.voidx.github.core.di.ext.DependenciesProvider
import com.voidx.github.di.ApplicationComponent
import com.voidx.github.di.DaggerApplicationComponent
import com.voidx.github.di.NavigationModule

class GitHubApp : Application(), DependenciesProvider<Any> {

    lateinit var appComponent: ApplicationComponent

    override fun dependencies() = appComponent

    override fun onCreate() {
        super.onCreate()
    }

    fun createApplicationComponent(activity: AppCompatActivity) {
        appComponent = DaggerApplicationComponent
            .builder()
            .navigationModule(NavigationModule(activity))
            .build()
    }
}