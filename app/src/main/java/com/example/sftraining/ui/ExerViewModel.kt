package com.example.sftraining.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sftraining.data.Exer

class ExerViewModel constructor(
    private val state: SavedStateHandle
) : ViewModel() {

    private val exerList: MutableLiveData<List<Exer>> by lazy {
        MutableLiveData<List<Exer>>().also {
            it.value = listOf(
                Exer(name = "Some name fjdkhsssssssssssssssssssssssssssssssssssssssk"),
                Exer(name = "Some name", favoriteNum = "453", commentsNum = "90"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name"),
                Exer(name = "Some name")
            )
        }
    }

    fun getExers(): LiveData<List<Exer>> {
        return exerList
    }

    private fun loadExers() {

    }
}
