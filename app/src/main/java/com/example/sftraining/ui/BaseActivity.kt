package com.example.sftraining.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.sftraining.R
import com.google.android.material.progressindicator.ProgressIndicator

open class BaseActivity : AppCompatActivity() {

    private lateinit var progressIndicator: ProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressIndicator = ProgressIndicator(this)
        progressIndicator.indicatorType = ProgressIndicator.LINEAR
        progressIndicator.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        progressIndicator.isIndeterminate = true //without progress
        progressIndicator.setBackgroundColor(resources.getColor(R.color.colorBlack))

    }

    //starting progress indicator animation
    fun startLoadingAnimation() {
        progressIndicator.show()
    }

    //hide progress indicator animation
    fun stopLoadingAnimation() {
        progressIndicator.hide()
    }

    fun setProgressIndicatorLayout(layoutId: Int){
        val layout = findViewById<RelativeLayout>(layoutId)
        layout.addView(progressIndicator)
        progressIndicator.hide()
    }
}