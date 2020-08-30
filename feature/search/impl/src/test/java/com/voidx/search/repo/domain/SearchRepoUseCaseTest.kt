package com.voidx.search.repo.domain

import com.voidx.github.utility.data.Mapper
import com.voidx.repo.data.model.Repo
import com.voidx.repo.model.RepoDTO
import com.voidx.search.data.repository.SearchRepository
import com.voidx.search.repo.domain.impl.SearchRepoUseCaseImpl
import com.voidx.search.repo.mockSuccessfullyResultsWithNextPage
import com.voidx.search.repo.mockSuccessfullyResultsWithoutNextPage
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Before
import org.junit.Test

class SearchRepoUseCaseTest {

    val repository: SearchRepository = mockk(relaxed = true)

    val mapper: Mapper<Repo, RepoDTO> = mockk(relaxed = true)

    private lateinit var useCase: SearchRepoUseCase

    @Before
    fun setup() {
        useCase = SearchRepoUseCaseImpl(repository, mapper)
    }

    @Test
    fun `check first page`() {

        every { mapper.map(any()) } returns RepoDTO()

        val paging = slot<Int>()

        every {
            repository.searchRepo(any(), any(), capture(paging))
        } returns
                mockSuccessfullyResultsWithNextPage()

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { it.size == 1 }
            .assertValue { paging.captured == 1 }
    }

    @Test
    fun `check loading more`() {

        every { mapper.map(any()) } returns RepoDTO()

        val paging = slot<Int>()

        every {
            repository.searchRepo(any(), any(), capture(paging))
        } returns
                mockSuccessfullyResultsWithNextPage()

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { it.size == 1 }
            .assertValue { paging.captured == 1 }

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { it.size == 1 }
            .assertValue { paging.captured == 2 }
    }

    @Test
    fun `check should not load more if does not have next page after first paging`() {

        every { mapper.map(any()) } returns RepoDTO()

        val paging = slot<Int>()

        every {
            repository.searchRepo(any(), any(), capture(paging))
        } returns
                mockSuccessfullyResultsWithoutNextPage()

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { it.size == 1 }
            .assertValue { paging.captured == 1 }

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { paging.captured == 1 }

    }

    @Test
    fun `check if paging was restarted`() {

        every { mapper.map(any()) } returns RepoDTO()

        val paging = slot<Int>()

        every {
            repository.searchRepo(any(), any(), capture(paging))
        } returns
                mockSuccessfullyResultsWithNextPage()

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { it.size == 1 }
            .assertValue { paging.captured == 1 }

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { it.size == 1 }
            .assertValue { paging.captured == 2 }

        useCase.resetSearchPaging()

        useCase
            .searchRepo()
            .test()
            .assertNoErrors()
            .assertValue { it.size == 1 }
            .assertValue { paging.captured == 1 }

    }

}