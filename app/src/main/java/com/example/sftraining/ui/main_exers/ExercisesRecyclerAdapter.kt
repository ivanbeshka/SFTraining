package com.example.sftraining.ui.main_exers

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.example.sftraining.model.Exer
import com.example.sftraining.databinding.ExerItemBinding
import com.example.sftraining.ui.registration.knowing.FirstFragment
import com.example.sftraining.ui.registration.knowing.KnowingViewPagerAdapter
import com.example.sftraining.ui.registration.knowing.SecondFragment
import com.example.sftraining.ui.registration.knowing.ThirdFragment
import com.google.android.material.imageview.ShapeableImageView
import com.rd.animation.type.AnimationType

class ExercisesRecyclerAdapter (private val lifecycle: Lifecycle, private val supportFragmentManager: FragmentManager, private var exersList: List<Exer>) :
    RecyclerView.Adapter<ExercisesRecyclerAdapter.ExerViewHolder>() {

    private lateinit var pagerAdapter: ExerViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    inner class ExerViewHolder(private val exerItemBinding: ExerItemBinding) :
        RecyclerView.ViewHolder(exerItemBinding.root) {

//        private val shapeableImageView =
//            itemView.findViewById<ShapeableImageView>(R.id.ei_main_shapableimage).apply {
//                val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
//                this.shapeAppearanceModel =
//                    this.shapeAppearanceModel.toBuilder().setAllCornerSizes(cornerSize).build()
//            }

        fun bind(exer: Exer) {
            exerItemBinding.exer = exer
            val fragments = arrayListOf<Fragment>()
            for (img in exer.imageUris){
                fragments.add(ImageFragment(img))
            }
//            val fragments = arrayListOf<Fragment>(
//                ImageFragment("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
//                FirstFragment(),
//                SecondFragment(),
//                ThirdFragment()
//            )

            pagerAdapter =
                ExerViewPagerAdapter(
                    fragments,
                    lifecycle,
                    supportFragmentManager
                )
            viewPager.adapter = pagerAdapter
//            shapeableImageView.apply {
//                Log.i("exer", exerItemBinding.exer.toString())
//                Log.i("images", exerItemBinding.exer?.imageUris.toString())
//                Glide.with(this).load(exerItemBinding.exer?.imageUris?.get(0)).placeholder(R.drawable.image2).into(this)
//            }
            exerItemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val exerItemBinding =
            DataBindingUtil.inflate<ExerItemBinding>(inflater, R.layout.exer_item, parent, false)
//        initView()
        viewPager = exerItemBinding.root.findViewById<ViewPager2>(R.id.knowing_viewpager)
        return ExerViewHolder(
            exerItemBinding
        )
    }

    override fun getItemCount(): Int {
        return exersList.size
    }

    override fun onBindViewHolder(holder: ExerViewHolder, position: Int) {
        val exer = exersList[position]
        holder.bind(exer)
    }

//    fun initView(){
//        viewPager = root.findViewById(R.id.knowing_viewpager)
//    }

}
