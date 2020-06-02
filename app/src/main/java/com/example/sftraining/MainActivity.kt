package com.example.sftraining

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView;
    private lateinit var recyclerAdapter: ExercisesRecyclerAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var bottomAppBar: BottomAppBar

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
                Log.d("home", "home")
                val bottomNavigation = BottomNavigation()
                bottomNavigation.show(supportFragmentManager, bottomNavigation.tag)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_bar)

        initView()

        val exerList = arrayListOf(
            "my first exer",
            "fehehshi",
            "ifwii",
            "sfsdfd",
            "asadsda",
            "my last exerd",
            " ",
            " ",
            " ",
            " dfksjfdjfsdjf",
            "fjsjdjsjdfjfsd"
        )

        recyclerAdapter = ExercisesRecyclerAdapter(exerList)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL


        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerAdapter


        fab.setOnClickListener {
            exerList.add("some exercise")
            recyclerView.adapter = ExercisesRecyclerAdapter(exerList)
        }
    }

    private fun initView() {
        bottomAppBar = findViewById(R.id.bottom_bar)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recycler_exercises)
    }
}
