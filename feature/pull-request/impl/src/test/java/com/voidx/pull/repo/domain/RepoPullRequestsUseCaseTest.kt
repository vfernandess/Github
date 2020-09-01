package com.voidx.pull.repo.domain

import com.voidx.core.data.Mapper
import com.voidx.pull.data.repository.PullRequestRepository
import com.voidx.pull.repo.domain.impl.RepoPullRequestUseCaseImpl
import com.voidx.pull.repo.domain.mapper.PullRequestToPullRequestDtoMapper
import com.voidx.pull.repo.mockEmptyPullRequests
import com.voidx.pull.repo.mockSuccessRepoPullRequests
import com.voidx.repo.model.RepoDTO
import com.voidx.user.data.model.User
import com.voidx.user.domain.model.UserDTO
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class RepoPullRequestsUseCaseTest {

    private val mapper: Mapper<User, UserDTO> = mockk(relaxed = true)
    private val repository: PullRequestRepository = mockk(relaxed = true)

    private lateinit var useCase: RepoPullRequestUseCase

    @Before
    fun setup() {
        useCase = RepoPullRequestUseCaseImpl(repository, PullRequestToPullRequestDtoMapper(mapper))
    }

    @Test
    fun `check successfully load pull requests`() {

        every { mapper.map(any()) } returns UserDTO().apply { login = "john.doe" }

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns mockSuccessRepoPullRequests()


        val repo = RepoDTO().apply {
            name = "repo.name"
            owner = UserDTO().apply {
                login = "owner.login"
            }
        }

        useCase
            .getPullRequestsFromRepo(repo)
            .test()
            .assertNoErrors()
            .assertValue { it.size == 2 }
    }

    @Test
    fun `check successfully load empty requests`() {

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns mockEmptyPullRequests()


        val repo = RepoDTO().apply {
            name = "repo.name"
            owner = UserDTO().apply {
                login = "owner.login"
            }
        }

        verify(exactly = 0) {
            mapper.map(any())
        }

        useCase
            .getPullRequestsFromRepo(repo)
            .test()
            .assertNoErrors()
            .assertValue { it.isEmpty() }
    }
}