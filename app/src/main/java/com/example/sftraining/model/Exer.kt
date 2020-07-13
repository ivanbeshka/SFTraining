package com.example.sftraining.model

import android.net.Uri
import java.util.*

// Exercise
data class Exer(
    val title: String = "",
    var titleImageUri: Uri,
    var imageUris: List<Uri>,
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
}