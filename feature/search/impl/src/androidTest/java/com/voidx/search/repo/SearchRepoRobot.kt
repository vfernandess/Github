package com.voidx.search.repo

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.voidx.search.R
import com.voidx.search.repo.view.SearchRepoFragment
import com.voidx.testing.ui.utility.espresso.matcher.withRecyclerViewItem
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import com.voidx.repo.R as repoR

class SearchRepoRobot(private val mockWebServer: MockWebServer) {

    fun start() = apply {
        launchFragmentInContainer<SearchRepoFragment>()
    }

    fun mockSuccessfullyWithNextResults() = apply {
        mockWebServer.dispatcher = SearchRepoMockFactory.SuccessWithNextDispatcher
    }

    fun mockSuccessfullyWithoutNextResults() = apply {
        mockWebServer.dispatcher = SearchRepoMockFactory.SuccessWithoutNextDispatcher
    }

    fun checkSuccessLoad() = apply {
        onView(
            withRecyclerViewItem(R.id.results, 0, repoR.id.repo_name)
        ).check(matches(withText("repo_name")))
        onView(withId(R.id.empty)).check(matches(not(isDisplayed())))
    }

}