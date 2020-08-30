package com.voidx.github.di

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.voidx.github.R
import dagger.Module
import dagger.Provides

@Module
class NavigationModule(private val activity: AppCompatActivity) {

    @Provides
    fun providesNavigation(): NavController {
        return activity.findNavController(R.id.fragment_container)
    }
}