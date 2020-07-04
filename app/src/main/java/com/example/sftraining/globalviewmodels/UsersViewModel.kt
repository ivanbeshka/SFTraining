package com.example.sftraining.globalviewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sftraining.model.User
import com.example.sftraining.repository.UsersRepository

class UsersViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val users: MutableLiveData<List<User?>> by lazy {
        MutableLiveData<List<User?>>().also {
            loadUsers()
        }
    }

    private fun loadUsers(){
        usersRepository.getUsers(
            {users.value = it.toList()},
            {}
        )
    }

    fun getUsers(): LiveData<List<User?>> {
        return users
    }

}