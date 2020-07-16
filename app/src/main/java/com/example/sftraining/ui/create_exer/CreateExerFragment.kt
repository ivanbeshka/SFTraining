package com.example.sftraining.ui.create_exer

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.example.sftraining.globalviewmodels.ExersViewModel
import com.example.sftraining.model.Exer
import com.example.sftraining.ui.camera.CameraActivity
import com.example.sftraining.ui.main.MainActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.abs


class CreateExerFragment : Fragment() {

    private lateinit var btnCreateExer: MaterialButton
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var btnAddTitleImage: FloatingActionButton
    private lateinit var titleImage: AppCompatImageView
    private lateinit var etTitle: TextInputEditText
    private lateinit var btnAddStartImage: MaterialButton
    private lateinit var btnAddMainImage: MaterialButton
    private lateinit var btnAddEndImage: MaterialButton
    private lateinit var imageStart: ShapeableImageView
    private lateinit var imageMain: ShapeableImageView
    private lateinit var imageEnd: ShapeableImageView
    private lateinit var isPrivate: MaterialCheckBox
    private lateinit var createExerAnimation: LottieAnimationView

    private val exersViewModel: ExersViewModel by activityViewModels()

    private val firebaseAuth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.create_exer_fragment, container, false)

        initView(root)

        initPickPhotoListeners()

        btnCreateExer.setOnClickListener {
            val activity = activity as MainActivity
            activity.startLoadingAnimation()

            val exer = Exer(
                imageUris = listOf(
                    imageStart.tag.toString(),
                    imageMain.tag.toString(),
                    imageEnd.tag.toString(),
                    titleImage.tag.toString()
                ),
                userUid = firebaseAuth.uid!!,
                isPrivate = isPrivate.isChecked,
                title = etTitle.text.toString()
            )

            exersViewModel.createExer(
                exer,
                onSuccess = {
                    activity.stopLoadingAnimation()
                    //on create exer animation
                    createExerAnimation.playAnimation()
                    createExerAnimation.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(p0: Animator?) {
                        }

                        override fun onAnimationEnd(p0: Animator?) {
                            findNavController(this@CreateExerFragment).navigate(R.id.navListOfExers)
                        }

                        override fun onAnimationCancel(p0: Animator?) {
                        }

                        override fun onAnimationStart(p0: Animator?) {
                        }
                    })
                },
                onFailure = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            )
        }

        //toolbar animation
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

    private fun pickPhotoFromGallery(view: View) {

        val getPhoto = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

            view.tag = uri.toString()
            Log.e("uri", uri.toString())

            if (view is ShapeableImageView) {
                Glide.with(view).load(uri)
                    .into(view.apply {
                        val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                        this.shapeAppearanceModel =
                            this.shapeAppearanceModel.toBuilder()
                                .setAllCornerSizes(cornerSize).build()
                    })

            } else {
                Glide.with(titleImage).load(uri).into(titleImage)
            }


        }
        getPhoto.launch("image/*")
    }

    private fun doPhoto(title: String){
        val intent = Intent(activity, CameraActivity::class.java)
        intent.putExtra("type", title.toString())
        startActivityForResult(intent, 1)
    }

    private fun initPickPhotoListeners() {
        btnAddTitleImage.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Choose")
                .setMessage("How do you want to choose image")
                .setNegativeButton("Take photo now") { dialog, which ->
                    doPhoto("title")
                }
                .setPositiveButton("From gallery") { dialog, which ->
                    pickPhotoFromGallery(titleImage)
                }
                .show()
        }

        btnAddStartImage.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Choose")
                .setMessage("How do you want to choose image")
                .setNegativeButton("Take photo now") { dialog, which ->
                    doPhoto("start")
                }
                .setPositiveButton("From gallery") { dialog, which ->
                    pickPhotoFromGallery(imageStart)
                }
                .show()

        }

        btnAddMainImage.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Choose")
                .setMessage("How do you want to choose image")
                .setNegativeButton("Take photo now") { dialog, which ->
                    doPhoto("main")
                }
                .setPositiveButton("From gallery") { dialog, which ->
                    pickPhotoFromGallery(imageMain)
                }
                .show()

        }

        btnAddEndImage.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Choose")
                .setMessage("How do you want to choose image")
                .setNegativeButton("Take photo now") { dialog, which ->
                    doPhoto("end")
                }
                .setPositiveButton("From gallery") { dialog, which ->
                    pickPhotoFromGallery(imageEnd)
                }
                .show()
        }
    }

    private fun initView(root: View) {
        btnCreateExer = root.findViewById(R.id.btn_create_exer)
        collapsingToolbarLayout = root.findViewById(R.id.ce_collapsing_layout)
        appBarLayout = root.findViewById(R.id.ce_app_bar_layout)
        toolbar = root.findViewById(R.id.ce_toolbar)
        btnAddTitleImage = root.findViewById(R.id.ce_fab_add_photo)
        titleImage = root.findViewById(R.id.ce_title_photo)
        titleImage.tag = ""
        btnAddStartImage = root.findViewById(R.id.ce_button_add_content_start)
        btnAddMainImage = root.findViewById(R.id.ce_button_add_content_main)
        btnAddEndImage = root.findViewById(R.id.ce_button_add_content_end)
        imageStart = root.findViewById(R.id.ce_image_view_content_start)
        imageStart.tag = ""
        imageMain = root.findViewById(R.id.ce_image_view_content_main)
        imageMain.tag = ""
        imageEnd = root.findViewById(R.id.ce_image_view_content_end)
        imageEnd.tag = ""
        isPrivate = root.findViewById(R.id.ce_checkbox_private)
        etTitle = root.findViewById(R.id.ce_title_edit_text)
        createExerAnimation = root.findViewById(R.id.create_exer_animation)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?)
    {
        if (data == null) {
            return
        }
        var Uri =  data.getStringExtra("uri")
        var type = data.getStringExtra("type")


        Log.d("IMAGE_URI", Uri.toString())
    }
}