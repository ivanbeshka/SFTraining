package com.example.sftraining

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sftraining.data.Exer
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.textview.MaterialTextView

class ExercisesRecyclerAdapter(private val exersList: List<Exer>) :
    RecyclerView.Adapter<ExercisesRecyclerAdapter.ExerViewHolder>() {

    class ExerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.exer_item, parent, false)) {

        private val tvExerName: MaterialTextView = itemView.findViewById(R.id.tv_exer_name)
        private val shapeableImageView =
            itemView.findViewById<ShapeableImageView>(R.id.shapeableImageView).apply {
                val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                this.shapeAppearanceModel =
                    this.shapeAppearanceModel.toBuilder().setAllCornerSizes(cornerSize).build()
            }


        fun bind(exer: Exer) {
            tvExerName.text = exer.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ExerViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return exersList.size
    }

    override fun onBindViewHolder(holder: ExerViewHolder, position: Int) {
        val exer = exersList[position]
        holder.bind(exer)
    }

}
