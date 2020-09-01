package com.voidx.pull.repo.domain.impl

import com.voidx.core.data.Mapper
import com.voidx.pull.data.model.PullRequest
import com.voidx.pull.data.repository.PullRequestRepository
import com.voidx.pull.repo.domain.RepoPullRequestUseCase
import com.voidx.pull.repo.domain.model.PullRequestDTO
import com.voidx.repo.model.RepoDTO
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepoPullRequestUseCaseImpl @Inject constructor (
    private val repository: PullRequestRepository,
    private val mapper: Mapper<PullRequest, PullRequestDTO>
): RepoPullRequestUseCase {

    override fun getPullRequestsFromRepo(repo: RepoDTO): Single<List<PullRequestDTO>> {
        return repository
            .getPullRequestsFromRepo(repo.owner?.login ?: "", repo.name ?: "")
            .map { prs ->
                prs.map { mapper.map(it) }
            }
    }
}