package com.example.sftraining.data

class Group(
    val organizer: User = User(),
    val members: List<User> = emptyList(),
    val avatarUrl: String = "",
    val chat: List<Msg> = emptyList()
) {
}