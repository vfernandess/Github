package com.voidx.pull.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.voidx.github.core.navigator.Navigator
import com.voidx.pull.repo.view.RepoPullRequestFragment
import com.voidx.repo.model.RepoDTO
import javax.inject.Inject

internal class PullRequestNavigatorImpl @Inject constructor(
    private val navigation: Navigator,
    private val context: Context
) : PullRequestNavigator {

    override fun showPullRequest(repo: RepoDTO) {
        navigation.navigateTo(RepoPullRequestFragment.newInstance(repo))
    }

    override fun openPullRequest(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}