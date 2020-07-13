package com.example.sftraining.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("android:text")
fun bindNum(materialTextView: MaterialTextView, value: Int) {
    if (value != 0) {
        materialTextView.text = value.toString()
    }
}

@BindingAdapter("android:src")
fun bindImage(imageView: ImageView, uri: Uri) {

        Glide.with(imageView).load(uri).into(imageView)

}