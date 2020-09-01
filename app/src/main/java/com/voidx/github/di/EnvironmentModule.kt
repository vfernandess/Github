package com.voidx.github.di

import com.voidx.core.data.Environment
import com.voidx.github.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface EnvironmentModule {

    companion object {

        @[Provides Singleton]
        fun providesEnvironment(): Environment {
            return Environment(BuildConfig.SERVER_URL, BuildConfig.DEBUG)
        }
    }
}