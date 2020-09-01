package com.voidx.github.core.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module()
interface CoreAndroidExposedModule {

    companion object {

        @[Provides]
        fun providesAndroidScheduler(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }
}