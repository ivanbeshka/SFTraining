package com.example.sftraining.data

// Exercise data class
data class Exer(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val videoUrl: String = "",
    val description: String = "",
    val tags: List<String> = emptyList(),
    val creator: String = "",
    val favoriteNum: Int = 0,
    val commentsNum: Int = 0,
    val comments: List<Msg> = emptyList(),
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