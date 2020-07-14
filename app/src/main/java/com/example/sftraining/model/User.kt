package com.example.sftraining.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val anon: Boolean = false,
    val avatarUri: String = "",
    val personInfo: String = "",
    val tag: String = ""
)