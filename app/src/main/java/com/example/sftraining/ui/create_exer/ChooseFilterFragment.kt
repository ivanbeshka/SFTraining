package com.example.sftraining.ui.create_exer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.sftraining.R
import com.example.sftraining.ui.base.BaseFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText

class ChooseFilterFragment :  Fragment() {

    private var title: String? = null
    private var start: String? = null
    private var main:  String? = null
    private var end:   String? = null

    private var titleURI: String? = null
    private var startURI: String? = null
    private var mainURI:  String? = null
    private var endURI:   String? = null

    private var youtubeID: String?  = null
    private var private:   Boolean? = null

    private lateinit var titleTextView: TextView
    private lateinit var imageTitle:    AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.choose_filters_fragment, container, false)

        getData()

        initView(root)


        return root
    }


    private fun getData(){
        val args = arguments?.let { ChooseFilterFragmentArgs.fromBundle(it) }

        title = args?.title
        start = args?.start
        main  = args?.main
        end   = args?.end

        titleURI = args?.titleURI
        startURI = args?.startURI
        mainURI  = args?.mainURI
        endURI   = args?.endURI

        youtubeID   = args?.youtubeVideoID
        private     = args?.isPrivate


    }

    private fun initView(root: View){
        imageTitle = root.findViewById(R.id.cf_title_photo)
        titleTextView = root.findViewById(R.id.cf_title_text_view)
        Glide.with(imageTitle).load(titleURI).into(imageTitle)
        titleTextView.setText(title)

    }

}