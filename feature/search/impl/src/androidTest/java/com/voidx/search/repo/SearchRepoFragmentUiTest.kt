package com.voidx.search.repo

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.voidx.testing.ui.utility.rule.RxIdlingImmediateRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchRepoFragmentUiTest {

    @Rule
    @JvmField
    val immediateSchedulersRule = RxIdlingImmediateRule()

    private val mockWebServer = MockWebServer().apply {
        try {
            start(8080)
        } catch (e: Exception) {
            Log.e("MockWebServer", e.message, e)
            e.printStackTrace()
        }
    }

    private lateinit var robot: SearchRepoRobot

    @Before
    fun setUp() {
        robot = SearchRepoRobot(mockWebServer)
    }

    @Test
    fun check_success_load() {
        robot
            .mockSuccessfullyWithNextResults()
            .start()
            .checkSuccessLoad()
    }
}