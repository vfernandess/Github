package com.voidx.search.repo

import com.voidx.testing.ui.utility.factory.createMockResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

sealed class SearchRepoMockFactory {

    object SuccessWithNextDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/search" -> createMockResponse(200, "search_repo_200.json").setHeader("link", "next")
                else -> createMockResponse(404)
            }
        }
    }

    object SuccessWithoutNextDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/search" -> createMockResponse(200, "search_repo_200.json")
                else -> createMockResponse(404)
            }
        }
    }
}