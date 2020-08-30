package com.voidx.repo.impl.domain

import com.voidx.github.utility.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import com.voidx.user.data.model.User
import com.voidx.user.domain.model.UserDTO

internal class RepoToRepoDtoMapper (
    private val userMapper: Mapper<@JvmSuppressWildcards User, @JvmSuppressWildcards UserDTO>
) : Mapper<Repo, RepoDTO> {

    override fun map(from: Repo): RepoDTO {
        return RepoDTO().apply {
            id = from.id
            name = from.name
            description = from.description
            forksCount = from.forksCount
            starsCount = from.starsCount
            owner = userMapper.map(from.owner)
        }
    }
}