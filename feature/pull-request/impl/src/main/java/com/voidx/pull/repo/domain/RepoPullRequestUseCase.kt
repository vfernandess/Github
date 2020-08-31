package com.voidx.pull.repo.domain

import com.voidx.pull.repo.domain.model.PullRequestDTO
import com.voidx.repo.model.RepoDTO
import io.reactivex.rxjava3.core.Single

interface RepoPullRequestUseCase {

    fun getPullRequestsFromRepo(repo: RepoDTO): Single<List<PullRequestDTO>>
}