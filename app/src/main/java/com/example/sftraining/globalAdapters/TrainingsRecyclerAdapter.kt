package com.example.sftraining.globalAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.example.sftraining.databinding.TrainingItemBinding
import com.example.sftraining.model.Training
import com.google.android.material.imageview.ShapeableImageView
import net.cachapa.expandablelayout.ExpandableLayout
import net.cachapa.expandablelayout.ExpandableLayout.OnExpansionUpdateListener


class TrainingsRecyclerAdapter(private var trainingsList: List<Training>) :
    RecyclerView.Adapter<TrainingsRecyclerAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(private val trainingItemBinding: TrainingItemBinding) :
        RecyclerView.ViewHolder(trainingItemBinding.root), View.OnClickListener,
        OnExpansionUpdateListener {

        private lateinit var expandableLayout: ExpandableLayout
        private lateinit var expandButton: ImageView

        fun bind(training: Training) {
            trainingItemBinding.training = training
            initView(trainingItemBinding.root)
            trainingItemBinding.root.findViewById<ShapeableImageView>(R.id.training_shapeable_image)
                .apply {
                    val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                    this.shapeAppearanceModel =
                        this.shapeAppearanceModel.toBuilder().setAllCornerSizes(cornerSize).build()
                    Glide.with(this.context).load(training.titleImageUri)
                        .placeholder(R.drawable.image2).centerCrop().into(this)
                }
            expandableLayout = trainingItemBinding.root.findViewById(R.id.expandable_layout)
            expandButton = trainingItemBinding.root.findViewById(R.id.expand_button)

            var index = 1

            for (exer in training.exers) {
                expandableLayout.addView(createTitleView(exer.title), index)
                ++index
            }

            expandableLayout.setOnExpansionUpdateListener(this)
            expandButton.setOnClickListener(this)
            trainingItemBinding.executePendingBindings()
        }

        private fun createTitleView(text: String): TextView {
            val tv = TextView(trainingItemBinding.root.context)
            tv.text = text
            return tv
        }

        fun initView(root: View) {
        }

        override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
            expandButton.rotation = expansionFraction * 180
        }

        override fun onClick(view: View?) {
            expandableLayout.toggle()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val trainingItemBinding =
            DataBindingUtil.inflate<TrainingItemBinding>(
                inflater,
                R.layout.training_item,
                parent,
                false
            )

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
