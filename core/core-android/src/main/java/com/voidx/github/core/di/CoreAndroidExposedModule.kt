package com.voidx.github.core.di

import com.voidx.github.core.network.RetrofitModule
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module(
    includes = [
        RetrofitModule::class
    ]
)
interface CoreAndroidExposedModule {

    companion object {

        @[Provides]
        fun providesAndroidScheduler(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }
}