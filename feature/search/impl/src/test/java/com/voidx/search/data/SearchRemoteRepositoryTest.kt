package com.voidx.search.data

import com.voidx.search.data.api.SearchAPI
import com.voidx.search.data.repository.impl.remote.SearchRemoteRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class SearchRemoteRepositoryTest {

    private val api: SearchAPI = mockk(relaxed = true)

    private lateinit var repository: SearchRemoteRepositoryImpl

    @Before
    fun setup() {
        repository = SearchRemoteRepositoryImpl(api)
    }

    @Test
    fun `check search repo has next page`() {

        every { api.searchRepo(any(), any(), any()) } returns mockSearchRepoWithNextHeaders()

        repository
            .searchRepo("options", "sort", 1)
            .test()
            .assertNoErrors()
            .assertValue { it.hasNextPage }
            .assertValue { it.items.isNotEmpty() }

    }

    @Test
    fun `check search repo does not have next page`() {

        every { api.searchRepo(any(), any(), any()) } returns mockSearchRepoWithoutNextHeaders()

        repository
            .searchRepo("options", "sort", 1)
            .test()
            .assertNoErrors()
            .assertValue { it.hasNextPage.not() }
            .assertValue { it.items.isNotEmpty() }

    }

}