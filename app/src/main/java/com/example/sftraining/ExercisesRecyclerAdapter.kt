package com.example.sftraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exer_item.view.*

class ExercisesRecyclerAdapter(private val exercisesList: List<String>) :
    RecyclerView.Adapter<ExercisesRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exer_item, parent, false) as View

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.textView.text = exercisesList[position]
    }

}
