package com.example.sftraining.globalViewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.sftraining.model.Exer
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
            description = "Description 1",
            exers = listOf(
                Exer(title = "exer 1"),
                Exer(title = "exer 2"),
                Exer(title = "exer 3"),
                Exer(title = "exer 4"),
                Exer(title = "exer 5"),
                Exer(title = "exer 6"),
                Exer(title = "exer 7"),
                Exer(title = "exer 8"),
                Exer(title = "exer 9"),
                Exer(title = "exer 10"),
                Exer(title = "exer 11"),
                Exer(title = "exer 12")
            ),
            titleImageUri = "https://www.dqindia.com/wp-content/uploads/2018/10/training.png"
        )
        val training2 = Training(name = "Training 2", description = "Description 2")
        val training3 = Training(
            name = "Training 3",
            description = "Description 3",
            exers = listOf(
                Exer(title = "exer 1"),
                Exer(title = "exer 2"),
                Exer(title = "exer 3"),
                Exer(title = "exer 4"),
                Exer(title = "exer 5"),
                Exer(title = "exer 6"),
                Exer(title = "exer 7"),
                Exer(title = "exer 8"),
                Exer(title = "exer 9"),
                Exer(title = "exer 10"),
                Exer(title = "exer 11"),
                Exer(title = "exer 12")
            ),
            titleImageUri = "https://www.dqindia.com/wp-content/uploads/2018/10/training.png"
        )
        val training4 = Training(
            name = "Training 4",
            description = "Description 4",
            exers = listOf(
                Exer(title = "exer 1"),
                Exer(title = "exer 2"),
                Exer(title = "exer 3"),
                Exer(title = "exer 4"),
                Exer(title = "exer 5"),
                Exer(title = "exer 6"),
                Exer(title = "exer 7"),
                Exer(title = "exer 8"),
                Exer(title = "exer 9"),
                Exer(title = "exer 10"),
                Exer(title = "exer 11"),
                Exer(title = "exer 12")
            ),
            titleImageUri = "https://www.dqindia.com/wp-content/uploads/2018/10/training.png"
        )
        return MutableLiveData(listOf(training1, training2, training3, training4))
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
