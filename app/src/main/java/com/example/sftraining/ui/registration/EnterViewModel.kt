package com.example.sftraining.ui.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.sftraining.model.User
import com.example.sftraining.repository.UsersRepository
import javax.inject.Inject

class EnterViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    //create user in db
    fun createUser(user: User){
        usersRepository.createUser(
            user = user,
            onComplete = {},
            onFailure = {}
        )
    }
}