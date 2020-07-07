package com.example.sftraining.globalviewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sftraining.model.Exer
import com.example.sftraining.repository.ExersRepository

class ExersViewModel @ViewModelInject constructor(
    @Assisted private val state: SavedStateHandle,
    private val exersRepository: ExersRepository
) : ViewModel() {

    companion object {
        const val SAVE_KEY = "exers_save_module"
    }

    private val exersData: MutableLiveData<List<Exer>> = state.getLiveData(SAVE_KEY)

    fun getExers(): LiveData<List<Exer>> {
        return exersData
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
