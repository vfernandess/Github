package com.voidx.testing.ui.utility.navigation

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider

inline fun <reified F: Fragment> startNavigation(
    @NavigationRes navigationGraph: Int,
    @StyleRes appStyle: Int,
    arguments: Bundle? = null): NavController {

    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    navController.setGraph(navigationGraph)

    val scenario = launchFragmentInContainer<F>(arguments, appStyle)

    scenario.onFragment { fragment ->
        Navigation.setViewNavController(fragment.requireView(), navController)
    }

    return navController
}