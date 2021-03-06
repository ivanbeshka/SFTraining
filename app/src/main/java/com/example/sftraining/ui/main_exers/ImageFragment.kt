package com.example.sftraining.ui.main_exers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.google.android.material.imageview.ShapeableImageView

class ImageFragment(private val imageUrl: String) : Fragment() {

    private lateinit var image: ShapeableImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_exer_image, container, false)

        initView(root)

        return root
    }

    private fun initView(root: View){
        root.findViewById<ShapeableImageView>(R.id.ei_main_shapableimage).apply {
            val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
            this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setAllCornerSizes(cornerSize).build()
            Glide.with(this.context).load(imageUrl).placeholder(R.drawable.image2).centerCrop().into(this)
        }
    }
}