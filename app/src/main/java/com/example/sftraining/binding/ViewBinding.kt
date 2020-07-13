package com.example.sftraining.binding

import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("android:text")
fun bindNum(materialTextView: MaterialTextView, value: Int){
    if (value != 0){
        materialTextView.text = value.toString()
    }
}
