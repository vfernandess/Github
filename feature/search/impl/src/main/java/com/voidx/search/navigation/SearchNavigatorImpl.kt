package com.voidx.search.navigation

import androidx.navigation.NavController
import com.voidx.search.R
import javax.inject.Inject

class SearchNavigatorImpl @Inject constructor (private val navigation: NavController) : SearchNavigator {

    override fun showRepoSearch() {
        navigation.navigate(R.id.action_global_searchRepoFragment)
    }
}