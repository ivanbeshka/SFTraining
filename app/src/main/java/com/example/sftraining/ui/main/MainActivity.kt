package com.example.sftraining.ui.main

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sftraining.R
import com.example.sftraining.ui.main.BaseActivity
import com.example.sftraining.ui.main_menus.FilterFragment
import com.example.sftraining.ui.main_menus.SearchFragment
import com.example.sftraining.ui.navigation.BottomNavigation
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

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
            R.id.action_filters -> {
                val filterFragment =
                    FilterFragment()
                filterFragment.show(supportFragmentManager, filterFragment.tag)
            }
            android.R.id.home -> {
                val bottomNavigation =
                    BottomNavigation()
                bottomNavigation.show(supportFragmentManager, bottomNavigation.tag)
            }
            R.id.action_search -> {
                val searchFragment = SearchFragment()
                searchFragment.dialog?.window?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorTransparent)))
                searchFragment.show(supportFragmentManager, searchFragment.tag)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(bottom_bar)

        when(intent.getStringExtra("user_type")){
            "anon" -> updateUIAnon()
        }

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

                R.id.navListOfFavoriteExers -> {
                    fab.hide()
                }
            }
        }
    }

    private fun updateUIAnon(){
        //TODO
    }

    private fun initView() {
        bottomAppBar = findViewById(R.id.bottom_bar)
        fab = findViewById(R.id.fab_add_exer)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}