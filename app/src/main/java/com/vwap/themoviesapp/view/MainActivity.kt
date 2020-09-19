package com.vwap.themoviesapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.vwap.themoviesapp.R
import kotlinx.android.synthetic.main.activity_layout.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        initToolbar(toolbarr)
        initNavFragment(toolbarr)
    }

    private fun initNavFragment(toolbarr: Toolbar) {
        supportFragmentManager.findFragmentById(R.id.nav_host)?.findNavController()?.let { nav ->
            toolbarr.setupWithNavController(nav, AppBarConfiguration(nav.graph))
        }
    }

    private fun initToolbar(toolbarr: Toolbar) {
        setSupportActionBar(toolbarr)
    }
}