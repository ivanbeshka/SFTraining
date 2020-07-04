package com.example.sftraining.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExersRepository : Repository {
    private val db = Firebase.firestore

}