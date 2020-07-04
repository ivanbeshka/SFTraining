package com.example.sftraining.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val anon: Boolean = false,
    val avatarUrl: String = "",
    val globalGroups: List<Group> = emptyList(),
    val myGroups: List<Group> = emptyList(),
    val globalTrainings: List<Training> = emptyList(),
    val myTrainings: List<Training> = emptyList(),
    val friends: List<User> = emptyList(),
    val favoriteExers: List<Exer> = emptyList()
)