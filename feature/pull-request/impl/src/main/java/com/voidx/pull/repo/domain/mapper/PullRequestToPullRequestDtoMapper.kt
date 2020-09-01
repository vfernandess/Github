package com.voidx.pull.repo.domain.mapper

import com.voidx.core.data.Mapper
import com.voidx.pull.data.model.PullRequest
import com.voidx.pull.repo.domain.model.PullRequestDTO
import com.voidx.user.data.model.User
import com.voidx.user.domain.model.UserDTO
import javax.inject.Inject

internal class PullRequestToPullRequestDtoMapper @Inject constructor(
    private val userMapper: Mapper<@JvmSuppressWildcards User, @JvmSuppressWildcards UserDTO>
) :Mapper<PullRequest, PullRequestDTO> {

    override fun map(from: PullRequest): PullRequestDTO {
        return PullRequestDTO().apply {
            title = from.title
            description = from.description
            url = from.url
            createdAt = from.createdAt
            owner = userMapper.map(from.owner)
        }
    }
}