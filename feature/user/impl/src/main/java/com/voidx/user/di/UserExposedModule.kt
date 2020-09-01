package com.voidx.user.di

import com.voidx.core.data.Mapper
import com.voidx.user.data.model.User
import com.voidx.user.domain.mapper.UserToUserDtoMapper
import com.voidx.user.domain.model.UserDTO
import dagger.Module
import dagger.Provides

@Module
interface UserExposedModule {

    companion object {

        @[Provides]
        fun providesUserMapper(): Mapper<User, UserDTO> {
            return UserToUserDtoMapper()
        }
    }
}