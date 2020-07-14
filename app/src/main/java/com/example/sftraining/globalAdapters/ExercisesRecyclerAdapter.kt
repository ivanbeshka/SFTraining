package com.example.sftraining.globalAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sftraining.R
import com.example.sftraining.binding.OnClick
import com.example.sftraining.databinding.ExerItemBinding
import com.example.sftraining.model.Exer
import com.example.sftraining.ui.base.BaseActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.imageview.ShapeableImageView



class ExercisesRecyclerAdapter (private var exersList: List<Exer>) : RecyclerView.Adapter<ExercisesRecyclerAdapter.ExerViewHolder>(){


    class ExerViewHolder(private val exerItemBinding: ExerItemBinding) : RecyclerView.ViewHolder(exerItemBinding.root) {

        private val shapeableImageView =
            itemView.findViewById<ShapeableImageView>(R.id.ei_main_shapableimage).apply {
                val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                this.shapeAppearanceModel =
                    this.shapeAppearanceModel.toBuilder().setAllCornerSizes(cornerSize).build()
            }


        fun bind(exer: Exer) {
            exerItemBinding.exer = exer
            exerItemBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val exerItemBinding =
            DataBindingUtil.inflate<ExerItemBinding>(inflater, R.layout.exer_item, parent, false)
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




}
