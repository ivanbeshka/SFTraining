package com.example.sftraining.data

data class Exer(
    var id: String = "",
    var name: String = "",
    var imageUrl: String = "",
    var videoUrl: String = "",
    var description: String = "",
    var tags: List<String> = emptyList(),
    var creator: String = ""
)