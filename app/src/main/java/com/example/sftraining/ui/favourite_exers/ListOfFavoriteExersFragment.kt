package com.example.sftraining.ui.favourite_exers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sftraining.R

class ListOfFavoriteExersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.list_of_favorite_exers, container, false)

        return root
    }

    private fun initView(root: View) {
    }
}