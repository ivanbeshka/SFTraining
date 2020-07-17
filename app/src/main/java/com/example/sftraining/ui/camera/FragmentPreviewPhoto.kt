package com.example.sftraining.ui.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sftraining.R
import com.example.sftraining.ui.base.BaseFragment

class FragmentPreviewPhoto : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.create_exer_fragment, container, false)

        initView(root)

        return root
    }
    override fun initView(root: View) {
        TODO("Not yet implemented")
    }
}