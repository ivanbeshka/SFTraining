package com.example.sftraining.ui.main_exers


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
import com.example.sftraining.globalAdapters.ExercisesRecyclerAdapter
import com.example.sftraining.globalviewmodels.ExersViewModel

class ExersFragment : Fragment() {

    private val exerViewModel: ExersViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: ExercisesRecyclerAdapter
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.list_of_exers, container, false)

        initView(root)

        exerViewModel.getExers().observe(viewLifecycleOwner, Observer { exers ->
            recyclerAdapter = ExercisesRecyclerAdapter(lifecycle, requireActivity().supportFragmentManager, exers)
            recyclerView.adapter = recyclerAdapter
        })

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        return root
    }

    private fun initView(root: View) {
        recyclerView = root.findViewById(R.id.recycler_exercises)
    }
}