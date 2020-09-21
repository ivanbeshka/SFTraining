package com.example.sftraining.ui.registration.knowing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sftraining.R
import com.google.android.material.button.MaterialButton

class FirstFragment : Fragment() {

    private lateinit var firstNextBtn: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_first_start, container, false)

        initView(root)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.knowing_viewpager)

        firstNextBtn.setOnClickListener {
            viewPager?.currentItem = 1
        }


        return root
    }

    private fun initView(root: View){
        firstNextBtn = root.findViewById(R.id.first_next_btn)
    }
}