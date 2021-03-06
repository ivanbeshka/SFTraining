package com.example.sftraining.model

import android.net.Uri
import android.view.View
import android.widget.ImageButton
import com.example.sftraining.R

// Exercise
data class Exer(
    val title: String = "",
//    var titleImageUri: String = "",
//    var imageUris: List<Uri>,
    var titleImageUri: String = "",
    var imageUris: List<String> = emptyList(),
    var videoUrl: String = "",
    val uid: String = "",
    val description: String = "",
    val isPrivate: Boolean = false,
    val userUid: String = "",
    val favoriteNum: Int = 0,
    val commentsNum: Int = 0,
    val execTimeMin: Int = 0
) {
    companion object {
        enum class Sports {
            CLIMBING, BODYBUILDING, RUNNING, WORKOUT,
            CALISTHENIC, BASKETBALL, ICE_CLIMBING, FOOTBALL
        }

        enum class Level {
            EASY, NORMAL, HARD
        }

        enum class BodyPart {
            NECK, SHOULDERS, BICEPS, TRICEPS, FOREARM,
            FINGERS, BACK, CHEST, ABS, LEGS
        }

        enum class TrainingType {
            CARDIO, POWER, STRETCHING, ENDURANCE,
            STATICS, DYNAMICS
        }

        enum class Weights {
            YOUR_WEIGHT, WEIGHTS
        }
    }

    fun onSuck(view: View){

        val imageButton : ImageButton = view.findViewById(R.id.ei_imagebutton_favorite)

        imageButton.setBackgroundResource(R.drawable.ic_favorite_border_24_pressed)

    }
}