package com.example.sftraining.utils

object Email {

    fun isValid(email: String): Boolean {
        val regex = Regex("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
        return email.matches(regex)
    }
}