package com.example.sftraining.repository

import com.example.sftraining.model.Training

class TrainingsRepository : Repository {
    fun addTraining(
        training: Training,
        onSuccess: () -> Unit,
        onFailure: (String?) -> Unit
    ) {
        //TODO: write body
    }
}