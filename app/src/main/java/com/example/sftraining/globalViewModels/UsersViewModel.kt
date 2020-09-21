package com.example.sftraining.globalViewModels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sftraining.model.User
import com.example.sftraining.repository.UsersRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UsersViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val users: MutableLiveData<List<User?>> by lazy {
        MutableLiveData<List<User?>>().also {
            loadUsers()
        }
    }

    private val currentUser: MutableLiveData<User?> by lazy {
        MutableLiveData<User?>().also {
            loadUser()
        }
    }

    private fun loadUsers() {
        usersRepository.getUsers(
            { users.value = it.toList() },
            {}
        )
    }

    fun getUsers(): LiveData<List<User?>> {
        return users
    }

    private fun loadUser() {

        val uid = Firebase.auth.uid!!

        usersRepository.getUser(
            uid,
            onComplete = {currentUser.value = it })
    }

    fun getUser(): LiveData<User?> {
        return currentUser
    }

}