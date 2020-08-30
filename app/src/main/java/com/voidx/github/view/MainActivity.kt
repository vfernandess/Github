package com.voidx.github.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.voidx.github.GitHubApp
import com.voidx.github.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as? GitHubApp)?.createApplicationComponent(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val finalHost = NavHostFragment.create(R.navigation.main_navigation)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, finalHost)
            .setPrimaryNavigationFragment(finalHost)
            .commit()
    }
}