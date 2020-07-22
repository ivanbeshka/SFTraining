package com.example.sftraining.ui.create_exer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sftraining.R
import com.example.sftraining.ui.base.BaseFragment

class ChooseFilterFragment :  Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.choose_filters_fragment, container, false)

        val args = arguments?.let { ChooseFilterFragmentArgs.fromBundle(it).titile }

        Toast.makeText(context, args, Toast.LENGTH_SHORT).show()
        return root
    }


}