package com.example.sftraining.globalviewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.sftraining.model.Training
import com.example.sftraining.repository.TrainingsRepository

class TrainingsViewModel @ViewModelInject constructor(
    @Assisted private val state: SavedStateHandle,
    private val trainingsRepository: TrainingsRepository
) : ViewModel() {

    companion object {
        const val SAVE_KEY = "trainings_save_module"
    }

//    private val trainingsData: MutableLiveData<List<Training>> = state.getLiveData(SAVE_KEY)

    fun getTrainings(): LiveData<List<Training>> {
        val training1 = Training(
            name = "Training 1",
            titleImageUri = "https://www.dqindia.com/wp-content/uploads/2018/10/training.png"
        )
        val training2 = Training(name = "Training 2")
        return MutableLiveData(listOf(training1, training2))
//        return trainingsData
    }

    fun createTraining(
        training: Training,
        onSuccess: () -> Unit = {},
        onFailure: (String?) -> Unit = {}
    ) {
        trainingsRepository.addTraining(training, onSuccess, onFailure)
    }

    private fun loadTrainings() {

    }

    fun cleanViewModel() {
        state.clearSavedStateProvider(SAVE_KEY)
    }

    fun saveViewModel() {
//        state.set(SAVE_KEY, exersData)
    }
}
