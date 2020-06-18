package com.example.sftraining.ui.create_exer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.create_exer_fragment.*
import kotlin.math.abs

class CreateExerFragment : Fragment() {

    private lateinit var btnCreateExer: MaterialButton
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var btnAddTitleImage: FloatingActionButton
    private lateinit var titleImage: AppCompatImageView
    private lateinit var btnAddStartImage: MaterialButton
    private lateinit var btnAddMainImage: MaterialButton
    private lateinit var btnAddEndImage: MaterialButton

    companion object {

        const val RC_GALLERY_TITLE = 100
        const val RC_GALLERY_START = 200
        const val RC_GALLERY_MAIN = 301
        const val RC_GALLERY_END = 400

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.create_exer_fragment, container, false)

        initView(root)

        initPickPhotoListeners()

        btnCreateExer.setOnClickListener {
            findNavController(this).navigate(R.id.navListOfExers)
        }

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                toolbar.background =
                    resources.getDrawable(R.color.colorPrimary, null)
            } else {
                toolbar.background =
                    resources.getDrawable(R.drawable.background_edit_text, null)
            }
        })

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_GALLERY_TITLE -> {
                val image: Uri? = data?.data
                Glide.with(titleImage).load(image).into(titleImage)
            }

            RC_GALLERY_START -> {
                val image: Uri? = data?.data
                val view = ce_image_view_content_start
                Glide.with(view).load(image)
                    .into(view.apply {
                        val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                        this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                            .setAllCornerSizes(cornerSize).build()
                    })
            }

            RC_GALLERY_MAIN -> {
                Log.e("contMain", "call")
                val image: Uri? = data?.data
                val view = ce_image_view_content_main
                Glide.with(view).load(image)
                    .into(view.apply {
                        val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                        this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                            .setAllCornerSizes(cornerSize).build()
                    })
            }

            RC_GALLERY_END -> {
                val image: Uri? = data?.data
                val view = ce_image_view_content_end
                Glide.with(view).load(image)
                    .into(view.apply {
                        val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                        this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                            .setAllCornerSizes(cornerSize).build()
                    })
            }
        }
    }

    private fun pickPhotoFromGallery(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        val mimeType = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        startActivityForResult(intent, requestCode)
    }

    private fun initPickPhotoListeners() {
        btnAddTitleImage.setOnClickListener {
            pickPhotoFromGallery(RC_GALLERY_TITLE)
        }

        btnAddStartImage.setOnClickListener {
            pickPhotoFromGallery(RC_GALLERY_START)
        }

        btnAddMainImage.setOnClickListener {
            pickPhotoFromGallery(RC_GALLERY_MAIN)
        }

        btnAddEndImage.setOnClickListener {
            pickPhotoFromGallery(RC_GALLERY_END)
        }
    }

    private fun initView(root: View) {
        btnCreateExer = root.findViewById(R.id.btn_create_exer)
        collapsingToolbarLayout = root.findViewById(R.id.ce_collapsing_layout)
        appBarLayout = root.findViewById(R.id.ce_app_bar_layout)
        toolbar = root.findViewById(R.id.ce_toolbar)
        btnAddTitleImage = root.findViewById(R.id.ce_fab_add_photo)
        titleImage = root.findViewById(R.id.ce_title_photo)
        btnAddStartImage = root.findViewById(R.id.ce_button_add_content_start)
        btnAddMainImage = root.findViewById(R.id.ce_button_add_content_main)
        btnAddEndImage = root.findViewById(R.id.ce_button_add_content_end)
    }
}