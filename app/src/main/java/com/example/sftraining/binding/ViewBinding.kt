package com.example.sftraining.binding

import androidx.databinding.BindingAdapter
import com.example.sftraining.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("attrNum")
fun bindNum(materialTextView: MaterialTextView, value: Int){
    if (value != 0){
        materialTextView.text = value.toString()
    }
}

@BindingAdapter("userPhoto")
fun bindPhoto(imageView: ShapeableImageView, uri: String?){
  if (uri != null && uri.isNotBlank()){
      //load photo
  } else {
      imageView.setImageResource(R.drawable.ic_account_circle_24px)
  }
}