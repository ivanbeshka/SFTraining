package com.example.sftraining.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val anon: Boolean = false,
    val avatarUrl: String = ""
)