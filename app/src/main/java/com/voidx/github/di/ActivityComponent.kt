package com.voidx.github.di

import com.voidx.github.view.MainActivity
import dagger.Component

@Component
interface ActivityComponent {

    fun inject(activity: MainActivity)
}