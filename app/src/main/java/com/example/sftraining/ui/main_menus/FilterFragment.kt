package com.example.sftraining.ui.main_menus

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.sftraining.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.slider.RangeSlider

class FilterFragment : BottomSheetDialogFragment() {

    private lateinit var rangeSlider: RangeSlider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.filters_fragment, container, false)
        initView(root)
        rangeSlider.setValues(10.0f, 60.0f)

        return root
    }

    private fun initView(root: View) {
        rangeSlider = root.findViewById(R.id.time_slider)

    }
}