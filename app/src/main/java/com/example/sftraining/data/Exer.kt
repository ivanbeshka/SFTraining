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
    val favoriteNum: String = "",
    val commentsNum: String = ""
)