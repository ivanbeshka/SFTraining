package com.example.sftraining

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {

    private lateinit var searchView: SearchView

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.search_filters_fragment, container, false)
        initView(root)

        searchView.setOnClickListener {
            searchView.onActionViewExpanded()
//            searchView.queryHint = getString(R.string.action_search)
        }

        return root
    }

    private fun initView(root: View) {
        searchView = root.findViewById(R.id.searchView)
    }
}