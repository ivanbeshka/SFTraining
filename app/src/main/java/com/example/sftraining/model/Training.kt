package com.example.sftraining.model

data class Training(
    val exers: List<Exer> = emptyList(),
    val timeMin: Int = exers.map { it.execTimeMin }.sum(),
    val name: String = "",
    val description: String = "",
    val creatorUid: String = "",
    val trainingUid: String = "",
    val favoriteNum: Int = 0,
    val commentsNum: Int = 0,
    var titleImageUri: String = ""
)