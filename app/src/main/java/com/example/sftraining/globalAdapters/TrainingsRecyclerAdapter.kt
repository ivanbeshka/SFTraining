package com.example.sftraining.globalAdapters

import android.content.Context
import android.graphics.Color
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
import com.google.android.material.textview.MaterialTextView
import net.cachapa.expandablelayout.ExpandableLayout
import net.cachapa.expandablelayout.ExpandableLayout.OnExpansionUpdateListener


class TrainingsRecyclerAdapter(private var trainingsList: List<Training>) :
    RecyclerView.Adapter<TrainingsRecyclerAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(private val trainingItemBinding: TrainingItemBinding) :
        RecyclerView.ViewHolder(trainingItemBinding.root), View.OnClickListener,
        OnExpansionUpdateListener {

        private lateinit var expandableLayout: ExpandableLayout
        private lateinit var exerTitlesLayout: LinearLayout
        private lateinit var expandButton: ImageView
        private lateinit var inflater: LayoutInflater

        fun bind(training: Training) {
            trainingItemBinding.training = training

            val root = trainingItemBinding.root

            root.findViewById<ShapeableImageView>(R.id.training_shapeable_image)
                .apply {
                    val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                    this.shapeAppearanceModel =
                        this.shapeAppearanceModel.toBuilder().setAllCornerSizes(cornerSize).build()
                    Glide.with(this.context).load(training.titleImageUri)
                        .placeholder(R.drawable.image2).centerCrop().into(this)
                }
            expandableLayout = root.findViewById(R.id.expandable_layout)
            exerTitlesLayout = root.findViewById(R.id.exer_titles_layout)
            expandButton = root.findViewById(R.id.expand_button)

            val list = root.findViewById(R.id.exer_titles_layout) as LinearLayout
            inflater = root.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            for (exer in training.exers) {
                val vi: TextView = inflater.inflate(android.R.layout.simple_list_item_1, null) as TextView
                vi.text = exer.title
                list.addView(vi)
            }

            Log.e("Child count", exerTitlesLayout.childCount.toString())

            expandableLayout.setOnExpansionUpdateListener(this)
            expandButton.setOnClickListener(this)
            trainingItemBinding.executePendingBindings()
        }

        private fun createTitleView(text: String): MaterialTextView {
            val descriptionText = trainingItemBinding.root.findViewById<MaterialTextView>(R.id.training_description)
            val tv = MaterialTextView(trainingItemBinding.root.context)
            tv.text = text
            tv.layoutParams = descriptionText.layoutParams
            tv.setTextColor(Color.BLACK)
            return tv
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
