package com.example.sftraining.ui.base

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    abstract fun initView(root: View)
}