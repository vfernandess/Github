package com.voidx.github.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.voidx.github.GitHubApp
import com.voidx.github.R
import com.voidx.github.core.di.ext.dependencies
import com.voidx.github.core.navigator.Destination
import com.voidx.github.core.navigator.Navigator
import com.voidx.photo.di.PhotoListDependencies
import com.voidx.search.di.SearchRepoDependencies

class MainActivity : AppCompatActivity(), Navigator, FragmentManager.OnBackStackChangedListener {

    companion object {

        const val UNIQUE_DESTINATION_COUNT = 1
    }

    val hasMoreDestinations: Boolean
        get() = supportFragmentManager.backStackEntryCount > UNIQUE_DESTINATION_COUNT

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as? GitHubApp)?.createApplicationComponent(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportFragmentManager.addOnBackStackChangedListener(this)

        val navigator = dependencies<PhotoListDependencies>().photoListNavigator()
        navigator.showPhotoList()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (hasMoreDestinations.not()) {
            return false
        }

        onBackPressed()
        return true
    }

    override fun navigateTo(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onBackStackChanged() {
        setBackEnabled(hasMoreDestinations)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        fragment?.let { changeTitle(it) }
    }

    private fun changeTitle(fragment: Fragment) {
        if (fragment is Destination) {
            supportActionBar?.title = fragment.getTitle() ?: getString(R.string.app_name)
        } else {
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    private fun setBackEnabled(enable: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enable)
        supportActionBar?.setDisplayShowHomeEnabled(enable)
    }
}
