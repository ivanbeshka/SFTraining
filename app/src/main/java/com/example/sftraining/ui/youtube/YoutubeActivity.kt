package com.example.sftraining.ui.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.sftraining.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubeActivity : YouTubeBaseActivity() {

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var onInitializatedListener: YouTubePlayer.OnInitializedListener
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        youTubePlayerView = findViewById(R.id.youtube_player_view)

        initUI()

        youTubePlayerView.initialize("AIzaSyDgZU-KD7eYxqqP8F3oQIBkqXh8uBAJOLA", onInitializatedListener)


    }

    private fun initUI(){
        onInitializatedListener = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youTubePlayer?.loadVideo("kxwiYcfdnPI")
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                TODO("Not yet implemented")
            }
        }
    }


}