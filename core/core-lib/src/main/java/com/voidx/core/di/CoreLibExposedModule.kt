package com.voidx.core.di

import com.voidx.core.data.network.RetrofitModule
import dagger.Module

@Module(
    includes = [
        RetrofitModule::class
    ]
)
interface CoreLibExposedModule