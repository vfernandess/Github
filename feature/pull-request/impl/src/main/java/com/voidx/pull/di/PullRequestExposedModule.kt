package com.voidx.pull.di

import android.content.Context
import com.voidx.core.data.Mapper
import com.voidx.github.core.navigator.Navigator
import com.voidx.pull.data.model.PullRequest
import com.voidx.pull.navigator.PullRequestNavigator
import com.voidx.pull.navigator.PullRequestNavigatorImpl
import com.voidx.pull.repo.domain.mapper.PullRequestToPullRequestDtoMapper
import com.voidx.pull.repo.domain.model.PullRequestDTO
import com.voidx.user.data.model.User
import com.voidx.user.domain.model.UserDTO
import dagger.Module
import dagger.Provides

@Module
interface PullRequestExposedModule {

    companion object {

        @Provides
        fun providesPullRequestNavigator(navigation: Navigator, context: Context): PullRequestNavigator {
            return PullRequestNavigatorImpl(navigation, context)
        }

        @Provides
        fun providesPullRequestToPullRequestDtoMapper(mapper: Mapper<@JvmSuppressWildcards User, @JvmSuppressWildcards UserDTO>): Mapper<PullRequest, PullRequestDTO> {
            return PullRequestToPullRequestDtoMapper(mapper)
        }
    }
}