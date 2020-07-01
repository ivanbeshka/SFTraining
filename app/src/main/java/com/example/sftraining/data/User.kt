package com.example.sftraining.data

class User(
    val name: String = "",
    val avatarUrl: String = "",
    val groups: Map<String, Group> = emptyMap(),
    val trainings: Map<String, Training> = emptyMap(),
    val friends: List<User> = emptyList(),
    val favoriteExers: List<Exer> = emptyList()
){

}