package com.example.sftraining.ui.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.example.sftraining.ui.base.BaseFragment
import java.io.ByteArrayOutputStream

class FragmentPreviewPhoto(private val uri: String) : BaseFragment(), View.OnClickListener{

    private lateinit var imageView: ImageView

    private lateinit var btnClose: ImageButton
    private lateinit var btnNice:  ImageButton

    private lateinit var btnRotateRight: ImageView
    private lateinit var btnRotateLeft:  ImageView

    private var angleRotate = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.preview_photo_fragment, container, false)

        initView(root)

        Glide.with(imageView).load(uri).into(imageView)

        return root
    }

    override fun initView(root: View) {
        imageView = root.findViewById(R.id.image_view_preview)

        btnClose = root.findViewById(R.id.image_btn_close)
        btnNice  = root.findViewById(R.id.image_btn_nice)

        btnRotateRight = root.findViewById(R.id.image_btn_rotate_right)
        btnRotateLeft  = root.findViewById(R.id.image_btn_rotate_left)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.image_btn_rotate_right -> rotateImageView(90)
            R.id.image_btn_rotate_left -> rotateImageView(-90)

            R.id.image_btn_close -> rotateImageView(90)
            R.id.image_btn_nice ->  rotateImageView(90)
        }
    }

    private fun rotateImageView(angle: Int){

        angleRotate += angle

        var myImg = BitmapFactory.decodeResource(resources, R.id.image_view_preview);

        var matrix = Matrix()
        matrix.postRotate(angle.toFloat());

        var rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.width, myImg.height,
        matrix, true);

        imageView.setImageBitmap(rotated)
    }

}