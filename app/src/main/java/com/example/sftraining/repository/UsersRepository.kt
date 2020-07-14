package com.example.sftraining.repository

import com.example.sftraining.model.User
import com.example.sftraining.repository.Repository.Companion.USER_PATH
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UsersRepository : Repository {
    private val db = Firebase.firestore

    fun createUser(
        user: User,
        onComplete: () -> Unit,
        onFailure: (String?) -> Unit
    ) {
        val docPath = db.collection(USER_PATH).document(user.uid)
        docPath.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val doc = task.result
                if (!doc?.exists()!!){
                    docPath.set(user)
                        .addOnCompleteListener {
                            onComplete()
                        }
                        .addOnFailureListener {
                            onFailure(it.message)
                        }
                } else {
                    onComplete()
                }
            }
        }
    }

    fun getUser(
        uid: String,
        onComplete: (User?) -> Unit = {},
        onFailure: (String?) -> Unit = {}
    ) {
        val doc = db.collection(USER_PATH).document(uid)
        doc.get()
            .addOnSuccessListener {
                val user: User? = it.toObject<User>()
                onComplete(user)
            }
            .addOnFailureListener {
                onFailure(it.message)
            }
    }

    fun getUsers(
        onComplete: (List<User?>) -> Unit,
        onFailure: (String?) -> Unit
    ) {
        val collection = db.collection(USER_PATH)

        collection.get(Source.DEFAULT)
            .addOnSuccessListener { snapshot ->
                val users: MutableList<User?> = mutableListOf()
                snapshot.documents.forEach {
                    users.add(it.toObject())
                }
                onComplete(users)
            }
            .addOnFailureListener {
                onFailure(it.message)
            }
    }
}