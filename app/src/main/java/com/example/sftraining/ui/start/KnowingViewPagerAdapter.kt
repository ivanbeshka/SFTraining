package com.example.sftraining.ui.start

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class KnowingViewPagerAdapter(
    private val fragments: ArrayList<Fragment>,
    lifecycle: Lifecycle,
    fm: FragmentManager
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount() =
        fragments.size

    override fun createFragment(position: Int) =
        fragments[position]

}