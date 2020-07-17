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
        val Exer1 = Exer(
            "Exer1",
            listOf(
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
                "https://images.unsplash.com/photo-1494548162494-384bba4ab999?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
                "https://image.shutterstock.com/image-photo/mountains-during-sunset-beautiful-natural-260nw-407021107.jpg",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"
            ),
            "1",
            "desc1",
            true,
            "uid1",
            1,
            1,
            1
        )
        val Exer2 = Exer(
            "Exer2",
            listOf(
                "https://images.unsplash.com/photo-1494548162494-384bba4ab999?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"
            ),
            "2",
            "desc2",
            true,
            "uid2",
            1,
            1,
            1
        )
        return MutableLiveData(listOf(Exer1, Exer2))
//        return exersData
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
