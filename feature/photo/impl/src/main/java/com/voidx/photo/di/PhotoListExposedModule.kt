package com.voidx.photo.di

import com.voidx.photo.navigator.PhotoListNavigator
import com.voidx.photo.navigator.PhotoListNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface PhotoListExposedModule {

    @[Binds]
    fun bindsPhotoListNavigator(impl: PhotoListNavigatorImpl): PhotoListNavigator
}
