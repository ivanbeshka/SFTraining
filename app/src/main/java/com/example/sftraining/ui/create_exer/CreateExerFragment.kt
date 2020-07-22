package com.example.sftraining.ui.create_exer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.example.sftraining.ui.camera.CameraActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.lang.Exception
import kotlin.math.abs

class CreateExerFragment : Fragment() {

    private lateinit var btnNext: MaterialButton
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var btnAddTitleImage: FloatingActionButton
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var btnAddStartImage: MaterialButton
    private lateinit var btnAddMainImage: MaterialButton
    private lateinit var btnAddEndImage: MaterialButton
    private lateinit var isPrivate: MaterialCheckBox
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var createExerAnimation: LottieAnimationView
    private lateinit var youtubePlayerGlobal: YouTubePlayer

    private lateinit var imageTitle: AppCompatImageView
    private lateinit var imageStart: ShapeableImageView
    private lateinit var imageMain:  ShapeableImageView
    private lateinit var imageEnd:   ShapeableImageView

    private lateinit var titleEditText: TextInputEditText
    private lateinit var startEditText: TextInputEditText
    private lateinit var mainEditText:  TextInputEditText
    private lateinit var endEditText:   TextInputEditText
    private lateinit var youtubeLink:   TextInputEditText

    companion object {
        const val TYPE_TITLE = "TITLE"
        const val TYPE_START = "START"
        const val TYPE_MAIN = "MAIN"
        const val TYPE_END = "END"
        const val URI = "uri"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.create_exer_fragment, container, false)

        initView(root)

        initPickPhotoListeners()


        btnNext.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val start: String = startEditText.text.toString()
            val main : String = mainEditText .text.toString()
            val end  : String = endEditText  .text.toString()



            val action = CreateExerFragmentDirections.actionNavCreateExerToChooseFilterFragment()

            findNavController(this).navigate(action)
        }

        youTubePlayerView.enableAutomaticInitialization = false
        youTubePlayerView.initialize(object : AbstractYouTubePlayerListener() {

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubePlayerGlobal = youTubePlayer
            }
        })

        textInputLayout.setEndIconOnClickListener {
            var txt: String = youtubeLink.text.toString()
            Log.d("LOGLOG", txt)
            try {
                txt = videoIdFromUrl(txt)
                youTubePlayerView.visibility = View.VISIBLE
                Toast.makeText(context, txt, Toast.LENGTH_SHORT).show()

                youtubePlayerGlobal.loadVideo(txt, 0F)

            }catch (e: Exception){
                Toast.makeText(context, "bad link", Toast.LENGTH_SHORT).show()
            }


        }


//        toolbar animation
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

            setPhoto(view, uri)
        }
        getPhoto.launch("image/*")
    }

    private fun setPhoto(view: View, uri: Uri){

        view.tag = uri.toString()

        if (view is ShapeableImageView) {
            Glide.with(view).load(uri)
                .into(view.apply {
                    val cornerSize: Float = resources.getDimension(R.dimen.cornerRadius)
                    this.shapeAppearanceModel =
                        this.shapeAppearanceModel.toBuilder()
                            .setAllCornerSizes(cornerSize).build()
                })

        } else {
            Glide.with(imageTitle).load(uri).into(imageTitle)
        }
    }

    private fun doPhoto(type: String) {

        val intent = Intent(activity, CameraActivity::class.java)

        val doPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val uri = Uri.parse(it.data?.getStringExtra(URI))
            when (type) {
                TYPE_TITLE -> {
                    setPhoto(imageTitle, uri)
                }
                TYPE_START -> {
                    setPhoto(imageStart, uri)
                }
                TYPE_MAIN -> {
                    setPhoto(imageMain, uri)
                }
                TYPE_END -> {
                    setPhoto(imageEnd, uri)
                }
            }
        }

        doPhoto.launch(intent)
    }

    private fun initPickPhotoListeners() {
        btnAddTitleImage.setOnClickListener {
            showDialog(TYPE_TITLE, it)
        }

        btnAddStartImage.setOnClickListener {
            showDialog(TYPE_START, it)
        }

        btnAddMainImage.setOnClickListener {
            showDialog(TYPE_MAIN, it)
        }

        btnAddEndImage.setOnClickListener {
            showDialog(TYPE_END, it)
        }
    }

    private fun showDialog(type: String, view: View) {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Choose")
            .setMessage("Where to get the photo")
            .setNegativeButton("Do photo now") { _, _ ->
                doPhoto(type)
            }
            .setPositiveButton("From gallery") { _, _ ->
                pickPhotoFromGallery(view)
            }
            .show()
    }

    private fun videoIdFromUrl(url: String) : String {
        return if (url.indexOf('?') == -1)
            if(url.indexOf('/') == -1)
                url
            else
                url.substringAfterLast('/')
        else
            url.substringAfterLast('=')

    }

    private fun initView(root: View) {
        btnNext = root.findViewById(R.id.btn_create_exer)
        collapsingToolbarLayout = root.findViewById(R.id.ce_collapsing_layout)
        appBarLayout = root.findViewById(R.id.ce_app_bar_layout)
        toolbar = root.findViewById(R.id.ce_toolbar)
        btnAddTitleImage = root.findViewById(R.id.ce_fab_add_photo)
        imageTitle = root.findViewById(R.id.ce_title_photo)
        btnAddStartImage = root.findViewById(R.id.ce_button_add_content_start)
        btnAddMainImage = root.findViewById(R.id.ce_button_add_content_main)
        btnAddEndImage = root.findViewById(R.id.ce_button_add_content_end)
        imageStart = root.findViewById(R.id.ce_image_view_content_start)
        imageMain = root.findViewById(R.id.ce_image_view_content_main)
        imageEnd = root.findViewById(R.id.ce_image_view_content_end)
        imageEnd.tag = ""
        youtubeLink = root.findViewById(R.id.ce_youtube_edit_text)
        youTubePlayerView = root.findViewById(R.id.youtube_player_view)
        isPrivate = root.findViewById(R.id.ce_checkbox_private)
        createExerAnimation = root.findViewById(R.id.create_exer_animation)
        textInputLayout = root.findViewById(R.id.textInputLayout4)

        titleEditText = root.findViewById(R.id.ce_title_edit_text)
        startEditText = root.findViewById(R.id.ce_start_edit_text)
        mainEditText  = root.findViewById(R.id.ce_main_edit_text)
        endEditText   = root.findViewById(R.id.ce_end_edit_text)
//           _        _
//          ( `-.__.-' )
//             `-.    .-'
//              \  /
//               ||
//               ||
//             \\
//               \\
//            ||    ||
//            ||____||
//            ||====||
//             \\
//              \\
//               ||
//               ||
//               ||
//               ||
//               ||
//               ||
//               ||
//               ||
//               []
//        this is very fucking for save image uri

        imageTitle.tag = ""
        imageStart.tag = ""
        imageMain.tag = ""
        imageEnd.tag = ""
    }

}


