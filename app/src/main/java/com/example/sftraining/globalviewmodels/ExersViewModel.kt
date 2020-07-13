package com.example.sftraining.globalviewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.sftraining.model.Exer
import com.example.sftraining.model.User
import com.example.sftraining.repository.ExersRepository

class ExersViewModel @ViewModelInject constructor(
    @Assisted private val state: SavedStateHandle,
    private val exersRepository: ExersRepository
) : ViewModel() {

    companion object {
        const val SAVE_KEY = "exers_save_module"
    }

    private val exersData: MutableLiveData<List<Exer>> = state.getLiveData(SAVE_KEY)

    private val exers: MutableLiveData<List<Exer>> = MutableLiveData(listOf(Exer(), Exer()))

    fun getExers(): LiveData<List<Exer>> {
//        return exersData
        return exers
    }

    fun createExer(
        exer: Exer,
        onSuccess: () -> Unit = {},
        onFailure: (String?) -> Unit = {}
    ) {
        exersRepository.addExer(exer, onSuccess, onFailure)
    }

    private fun loadExers() {

    }

    fun cleanViewModel() {
        state.clearSavedStateProvider(SAVE_KEY)
    }

    fun saveViewModel() {
        state.set(SAVE_KEY, exersData)
    }
}
