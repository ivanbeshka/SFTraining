package com.example.sftraining.ui.main_exers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.sftraining.R
import com.example.sftraining.binding.OnClick
import com.example.sftraining.databinding.ExerItemBinding
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType
import com.example.sftraining.globalviewmodels.ExersViewModel
import com.example.sftraining.model.Exer
import com.example.sftraining.ui.base.BaseActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.imageview.ShapeableImageView

class ExercisesRecyclerAdapter (private val lifecycle: Lifecycle, private val supportFragmentManager: FragmentManager, private var exersList: List<Exer>) :
    RecyclerView.Adapter<ExercisesRecyclerAdapter.ExerViewHolder>() {

    private lateinit var pagerAdapter: ExerViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerDots: PageIndicatorView

    inner class ExerViewHolder(private val exerItemBinding: ExerItemBinding) :
        RecyclerView.ViewHolder(exerItemBinding.root) {

        fun bind(exer: Exer) {
            exerItemBinding.exer = exer
            val fragments = arrayListOf<Fragment>()
            for (img in exer.imageUris){
                fragments.add(ImageFragment(img))
            }
            initView(exerItemBinding.root)
            pagerAdapter =
                ExerViewPagerAdapter(
                    fragments,
                    lifecycle,
                    supportFragmentManager
                )
            viewPager.adapter = pagerAdapter
            pagerDots.count = fragments.size
            pagerDots.setAnimationType(AnimationType.DROP)
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
//                    pagerDots.selection = position
                    exerItemBinding.root.findViewById<PageIndicatorView>(R.id.page_indicator).selection = position
                }
            })
            exerItemBinding.executePendingBindings()
        }

        fun initView(root: View){
            viewPager = root.findViewById(R.id.knowing_viewpager)
            pagerDots = root.findViewById(R.id.page_indicator)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val exerItemBinding =
            DataBindingUtil.inflate<ExerItemBinding>(inflater, R.layout.exer_item, parent, false)

        return ExerViewHolder(exerItemBinding)
    }

    override fun getItemCount(): Int {
        return exersList.size
    }

    override fun onBindViewHolder(holder: ExerViewHolder, position: Int) {
        val exer = exersList[position]
        holder.bind(exer)
    }

}
