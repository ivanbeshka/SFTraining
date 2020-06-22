package com.example.sftraining.ui.registration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sftraining.R
import com.google.android.material.button.MaterialButton

class ThirdFragment : Fragment(){

    private lateinit var thirdPrevBtn: MaterialButton
    private lateinit var thirdFinishBtn: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.third_start_fragment, container, false)

        initView(root)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.knowing_viewpager)

        thirdPrevBtn.setOnClickListener {
            viewPager?.currentItem = 2
        }

        thirdFinishBtn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
            onBoardingFinished()
        }

        return root
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    private fun initView(root: View){
        thirdFinishBtn = root.findViewById(R.id.finish_knowing_btn)
        thirdPrevBtn = root.findViewById(R.id.third_prev_btn)
    }
}