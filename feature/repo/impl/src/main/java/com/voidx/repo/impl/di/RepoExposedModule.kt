package com.voidx.repo.impl.di

import com.voidx.core.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.impl.domain.RepoToRepoDtoMapper
import com.voidx.repo.model.RepoDTO
import com.voidx.user.data.model.User
import com.voidx.user.domain.model.UserDTO
import dagger.Module
import dagger.Provides

@Module
interface RepoExposedModule {

    companion object {

        @[Provides]
        fun providesRepoToRepoDtoMapper(userMapper: Mapper<User, UserDTO>): Mapper<Repo, RepoDTO> {
            return RepoToRepoDtoMapper(userMapper)
        }
    }
}