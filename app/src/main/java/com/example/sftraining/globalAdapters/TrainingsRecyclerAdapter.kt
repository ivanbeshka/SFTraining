package com.example.sftraining.globalAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.example.sftraining.databinding.TrainingItemBinding
import com.example.sftraining.model.Training
import com.google.android.material.imageview.ShapeableImageView

class TrainingsRecyclerAdapter (private var trainingsList: List<Training>) :
    RecyclerView.Adapter<TrainingsRecyclerAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(private val trainingItemBinding: TrainingItemBinding) :
        RecyclerView.ViewHolder(trainingItemBinding.root) {

        fun bind(training: Training) {
            trainingItemBinding.training = training
            initView(trainingItemBinding.root)
            trainingItemBinding.root.findViewById<ShapeableImageView>(R.id.training_shapeable_image).apply {
                val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setAllCornerSizes(cornerSize).build()
                Glide.with(this.context).load(training.titleImageUri).placeholder(R.drawable.image2).centerCrop().into(this)
            }
            trainingItemBinding.executePendingBindings()
        }

        fun initView(root: View){
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
