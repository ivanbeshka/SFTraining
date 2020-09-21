package com.example.sftraining.ui.registration.knowing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sftraining.R
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType

class KnowingFragment : Fragment() {

    private lateinit var pagerAdapter: KnowingViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerDots: PageIndicatorView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_knowing_viewpager, container, false)

        initView(root)

        val fragments = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        pagerAdapter =
            KnowingViewPagerAdapter(
                fragments,
                lifecycle,
                requireActivity().supportFragmentManager
            )
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                pagerDots.selection = position
            }
        })

        return root
    }

    private fun initView(root: View) {
        viewPager = root.findViewById(R.id.knowing_viewpager)
        pagerDots = root.findViewById(R.id.page_indicator)
        pagerDots.setAnimationType(AnimationType.DROP)
    }
}