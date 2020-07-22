package com.example.sftraining.globalAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.sftraining.R
import com.example.sftraining.databinding.TrainingItemBinding
import com.example.sftraining.model.Training

class TrainingsRecyclerAdapter (private val lifecycle: Lifecycle, private val fragmentManager: FragmentManager, private var trainingsList: List<Training>) :
    RecyclerView.Adapter<TrainingsRecyclerAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(private val trainingItemBinding: TrainingItemBinding) :
        RecyclerView.ViewHolder(trainingItemBinding.root) {

        fun bind(training: Training) {
            trainingItemBinding.training = training
//            val fragments = arrayListOf<Fragment>()
//            for (img in trainings.imageUris){
//                fragments.add(ImageFragment(img))
//            }
            initView(trainingItemBinding.root)
//            pagerAdapter =
//                ExerViewPagerAdapter(
//                    fragments,
//                    lifecycle,
//                    fragmentManager
//                )
//            viewPager.adapter = pagerAdapter
//            pagerDots.count = fragments.size
//            pagerDots.setAnimationType(AnimationType.DROP)
//            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//                override fun onPageSelected(position: Int) {
//                    pagerDots.selection = position
//                    exerItemBinding.root.findViewById<PageIndicatorView>(R.id.page_indicator).selection = position
//                }
//            })
//            exerItemBinding.executePendingBindings()
        }

        fun initView(root: View){
//            viewPager = root.findViewById(R.id.knowing_viewpager)
//            pagerDots = root.findViewById(R.id.page_indicator)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val trainingItemBinding =
            DataBindingUtil.inflate<TrainingItemBinding>(inflater, R.layout.training_item, parent, false)

        return TrainingViewHolder(trainingItemBinding)
    }

    override fun getItemCount(): Int {
        return trainingsList.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val training = trainingsList[position]
        holder.bind(training)
    }

}
