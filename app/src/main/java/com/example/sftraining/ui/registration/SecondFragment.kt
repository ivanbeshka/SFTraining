package com.example.sftraining.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sftraining.R
import com.google.android.material.button.MaterialButton

class SecondFragment : Fragment() {

    private lateinit var secondNextBtn: MaterialButton
    private lateinit var secondPrevBtn: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.second_start_fragment, container, false)

        initView(root)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.knowing_viewpager)

        secondPrevBtn.setOnClickListener {
            viewPager?.currentItem = 0
        }

        secondNextBtn.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return root
    }

    private fun initView(root: View){
        secondNextBtn = root.findViewById(R.id.second_next_btn)
        secondPrevBtn = root.findViewById(R.id.second_prev_btn)
    }
}