package com.example.sftraining.model

data class Msg(
    val creator: User = User(),
    val content: Any = Any()
)