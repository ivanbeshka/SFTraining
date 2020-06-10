package com.example.sftraining.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sftraining.R

class ListOfExersFragment : Fragment() {

    private val exerViewModel: ExerViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: ExercisesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.list_of_exers, container, false)

        initView(root)

        exerViewModel.getExers().observe( viewLifecycleOwner, Observer { t ->
            recyclerAdapter = ExercisesRecyclerAdapter(t)
            recyclerView.adapter = recyclerAdapter
        })

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        return root
    }

    private fun initView(root: View){
        recyclerView = root.findViewById(R.id.recycler_exercises)
    }
}