package com.voidx.search.repo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidx.core.data.Mapper
import com.voidx.github.core.presentation.State
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import com.voidx.search.data.repository.SearchRepository
import com.voidx.search.repo.domain.SearchRepoUseCase
import com.voidx.search.repo.domain.impl.SearchRepoUseCaseImpl
import com.voidx.search.repo.mockEmptyResults
import com.voidx.search.repo.mockSuccessfullyResultsWithNextPage
import com.voidx.search.repo.mockSuccessfullyResultsWithoutNextPage
import com.voidx.testing.ui.utility.rule.RxAndroidImmediateRule
import com.voidx.testing.unit.utility.rule.RxJavaImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchRepoViewModelTest {

    private val mapper: Mapper<Repo, RepoDTO> = mockk(relaxed = true)
    private val repository: SearchRepository = mockk(relaxed = true)

    lateinit var viewModel: SearchRepoViewModel
    lateinit var useCase: SearchRepoUseCase

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
        useCase = SearchRepoUseCaseImpl(repository, mapper)
        viewModel = SearchRepoViewModel(useCase, AndroidSchedulers.mainThread())
    }

    @Test
    fun `check successfully loaded`() {

        every { mapper.map(any()) } returns RepoDTO()

        every {
            repository.searchRepo(any(), any(), any())
        } returns
                mockSuccessfullyResultsWithNextPage()

        viewModel.load()

        assertEquals(State.Success, viewModel.state().value)
        assertEquals(1, viewModel.results().value?.size)

    }

    @Test
    fun `check successfully pagination`() {

        every { mapper.map(any()) } returns RepoDTO()

        every {
            repository.searchRepo(any(), any(), any())
        } returns
                mockSuccessfullyResultsWithNextPage()

        viewModel.load()
        viewModel.load()

        assertEquals(false, useCase.isFirstPage())
        assertEquals(2, viewModel.results().value?.size)
    }

    @Test
    fun `check successfully restart after pagination`() {

        every { mapper.map(any()) } returns RepoDTO()

        every {
            repository.searchRepo(any(), any(), any())
        } returns
                mockSuccessfullyResultsWithNextPage()

        viewModel.load()
        viewModel.load()

        assertEquals(false, useCase.isFirstPage())
        assertEquals(2, viewModel.results().value?.size)

        viewModel.reload()

        assertEquals(true, useCase.isFirstPage())
        assertEquals(1, viewModel.results().value?.size)
    }

    @Test
    fun `check empty state only for empty results and first page `() {

        every { mapper.map(any()) } returns RepoDTO()

        every {
            repository.searchRepo(any(), any(), any())
        } returns
                mockEmptyResults()

        viewModel.load()

        assertEquals(State.Empty, viewModel.state().value)
        assertEquals(true, useCase.isFirstPage())
        assertEquals(0, viewModel.results().value?.size)
    }

    @Test
    fun `check success state after paging with empty list`() {

        every { mapper.map(any()) } returns RepoDTO()

        every {
            repository.searchRepo(any(), any(), any())
        } returns
                mockSuccessfullyResultsWithNextPage()

        viewModel.load()

        every {
            repository.searchRepo(any(), any(), any())
        } returns
                mockEmptyResults()

        viewModel.load()

        assertEquals(State.Success, viewModel.state().value)
        assertEquals(1, viewModel.results().value?.size)
        assertEquals(true, useCase.isFirstPage())
    }

    @Test
    fun `check should not paging or change success after having results when does not have next page`() {

        every { mapper.map(any()) } returns RepoDTO()

        every {
            repository.searchRepo(any(), any(), any())
        } returns
                mockSuccessfullyResultsWithoutNextPage()

        viewModel.load()
        viewModel.load()
        viewModel.load()

        assertEquals(State.Success, viewModel.state().value)
        assertEquals(1, viewModel.results().value?.size)
        assertEquals(true, useCase.isFirstPage())
    }

}