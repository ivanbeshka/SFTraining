package com.example.sftraining.ui.trainings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sftraining.R
import com.example.sftraining.globalAdapters.TrainingsRecyclerAdapter
import com.example.sftraining.globalviewmodels.TrainingsViewModel

class TrainingsFragment : Fragment() {

    private val trainingsViewModel: TrainingsViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: TrainingsRecyclerAdapter
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.trainings_fragment, container, false)

        initView(root)

        trainingsViewModel.getTrainings().observe(viewLifecycleOwner, Observer { trainings ->
            recyclerAdapter = TrainingsRecyclerAdapter(
                trainings
            )
            recyclerView.adapter = recyclerAdapter
        })

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        return root
    }

    private fun initView(root: View) {
        recyclerView = root.findViewById(R.id.recycler_trainings)
    }
}