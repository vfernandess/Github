package com.voidx.pull.repo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidx.github.core.presentation.State
import com.voidx.core.data.Mapper
import com.voidx.pull.data.model.PullRequest
import com.voidx.pull.data.repository.PullRequestRepository
import com.voidx.pull.repo.domain.RepoPullRequestUseCase
import com.voidx.pull.repo.domain.impl.RepoPullRequestUseCaseImpl
import com.voidx.pull.repo.domain.model.PullRequestDTO
import com.voidx.pull.repo.mockEmptyPullRequests
import com.voidx.pull.repo.mockErrorPullRequests
import com.voidx.pull.repo.mockSuccessRepoPullRequests
import com.voidx.repo.model.RepoDTO
import com.voidx.testing.ui.utility.rule.RxAndroidImmediateRule
import com.voidx.testing.unit.utility.rule.RxJavaImmediateSchedulerRule
import com.voidx.user.domain.model.UserDTO
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepoPullRequestViewModelTest {

    private val mapper: Mapper<PullRequest, PullRequestDTO> = mockk(relaxed = true)
    private val repository: PullRequestRepository = mockk(relaxed = true)

    private lateinit var viewModel: RepoPullRequestViewModel
    private lateinit var useCase: RepoPullRequestUseCase
    private lateinit var repo: RepoDTO

    @Rule
    @JvmField
    val rxJavaImmediateSchedulerRule = RxJavaImmediateSchedulerRule()

    @Rule
    @JvmField
    val rxAndroidImmediateRule = RxAndroidImmediateRule()

    @Rule
    @JvmField
    val immediateLiveData = InstantTaskExecutorRule()

    @Before
    fun setup() {
        useCase = RepoPullRequestUseCaseImpl(repository, mapper)
        viewModel = RepoPullRequestViewModel(useCase, AndroidSchedulers.mainThread())
        repo = RepoDTO().apply {
            name = "repo.name"
            owner = UserDTO().apply {
                login = "owner.login"
            }
        }
    }

    @Test
    fun `check successfully loaded`() {

        every { mapper.map(any()) } returns PullRequestDTO()

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns
                mockSuccessRepoPullRequests()

        viewModel.load(repo)

        verify {
            repository.getPullRequestsFromRepo("owner.login", "repo.name")
        }

        assertEquals(2, viewModel.prs().value?.size)
        assertEquals(State.Success, viewModel.state().value)
    }

    @Test
    fun `check empty success`() {

        every { mapper.map(any()) } returns PullRequestDTO()

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns
                mockEmptyPullRequests()

        viewModel.load(repo)

        verify {
            repository.getPullRequestsFromRepo("owner.login", "repo.name")
        }

        assertEquals(0, viewModel.prs().value?.size)
        assertEquals(State.Empty, viewModel.state().value)
    }

    @Test
    fun `check empty after successfully load`() {

        every { mapper.map(any()) } returns PullRequestDTO()

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns
                mockSuccessRepoPullRequests()

        viewModel.load(repo)

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns
                mockEmptyPullRequests()

        viewModel.load(repo)

        assertEquals(0, viewModel.prs().value?.size)
        assertEquals(State.Empty, viewModel.state().value)
    }

    @Test
    fun `check state error`() {

        every { mapper.map(any()) } returns PullRequestDTO()

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns
                mockErrorPullRequests()

        viewModel.load(repo)

        assertEquals(0, viewModel.prs().value?.size)
        assertEquals(State.Error, viewModel.state().value)
    }

    @Test
    fun `check state error after successfully load`() {
        every { mapper.map(any()) } returns PullRequestDTO()

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns
                mockSuccessRepoPullRequests()

        viewModel.load(repo)

        assertEquals(2, viewModel.prs().value?.size)
        assertEquals(State.Success, viewModel.state().value)

        every {
            repository.getPullRequestsFromRepo(any(), any())
        } returns
                mockErrorPullRequests()

        viewModel.load(repo)

        assertEquals(0, viewModel.prs().value?.size)
        assertEquals(State.Error, viewModel.state().value)
    }
}