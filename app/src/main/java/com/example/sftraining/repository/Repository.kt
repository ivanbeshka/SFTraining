package com.example.sftraining.repository

interface Repository {

    companion object {
        const val EXER_PATH = "exers"
        const val USER_PATH = "users"
        const val PRIVATE_EXERS_PATH = "private exers"
        const val FRIENDS_PATH = "friends"
        const val GROUPS_PATH = "groups"
        const val FAVORITE_EXERS_PATH = "favorite exers"
    }
}