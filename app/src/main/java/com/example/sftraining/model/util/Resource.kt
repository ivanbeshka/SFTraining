package com.example.sftraining.model.util

data class Resource<T>(
    val status: Status,
    val data: T?,
    val exception: Exception?
) {
    enum class Status {
        LOADING,
        COMPLETED
    }
}