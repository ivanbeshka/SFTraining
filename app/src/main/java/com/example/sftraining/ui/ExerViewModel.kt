package com.example.sftraining.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sftraining.data.Exer

class ExerViewModel : ViewModel() {

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
                Exer(name = "Some name"))
        }
    }

    fun getExers(): LiveData<List<Exer>> {
        return exerList
    }

    private fun loadExers() {

    }
}
