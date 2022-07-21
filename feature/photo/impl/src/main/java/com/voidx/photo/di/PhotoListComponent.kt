package com.voidx.photo.di

import android.content.Context
import com.google.gson.Gson
import com.voidx.github.core.navigator.Navigator
import com.voidx.photo.navigator.PhotoListNavigator
import com.voidx.photo.ui.view.PhotoListFragment
import dagger.Component
import io.reactivex.rxjava3.core.Scheduler
import retrofit2.Retrofit

@Component(
    modules = [
        PhotoListInternalModule::class
    ],
    dependencies = [
        PhotoListDependencies::class
    ]
)
internal interface PhotoListComponent {

    fun inject(fragment: PhotoListFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: PhotoListDependencies): PhotoListComponent
    }
}

interface PhotoListDependencies {
    fun context(): Context
    fun gson(): Gson
    fun retrofit(): Retrofit
    fun androidScheduler(): Scheduler
    fun navigation(): Navigator
    fun photoListNavigator(): PhotoListNavigator
}
