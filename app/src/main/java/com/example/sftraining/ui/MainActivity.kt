package com.example.sftraining.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sftraining.R
import com.example.sftraining.ui.create_exer.CreateExerFragment
import com.example.sftraining.ui.navigation.BottomNavigation
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_bar_exers, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val filterFragment = FilterFragment()
                filterFragment.show(supportFragmentManager, filterFragment.tag)
            }
            android.R.id.home -> {
                val bottomNavigation =
                    BottomNavigation()
                bottomNavigation.show(supportFragmentManager, bottomNavigation.tag)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(bottom_bar)

        initView()

        fab.setOnClickListener {
            navController.navigate(R.id.navCrateExer)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navCrateExer -> {

                    bottomAppBar.performHide()
                    fab.hide()
                }

                R.id.navListOfExers -> {
                    fab.show()
                    bottomAppBar.performShow()

                }
            }
        }
    }

    private fun initView() {
        bottomAppBar = findViewById(R.id.bottom_bar)
        fab = findViewById(R.id.fab_add_exer)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}