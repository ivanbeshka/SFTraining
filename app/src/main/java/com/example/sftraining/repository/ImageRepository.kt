package com.example.sftraining.repository

import android.net.Uri
import android.util.Log
import com.example.sftraining.model.Exer
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ImageRepository : Repository {
    private val storageReference = Firebase.storage.reference

    companion object {
        const val EXER_IMAGES_PATH = "images"
    }

    fun addExerImages(exer: Exer) {

        val path = storageReference.child(exer.userUid).child(EXER_IMAGES_PATH)

        for (imageUri in exer.imageUris) {
            if (imageUri.isNotBlank()) {
                val uri = Uri.parse(imageUri)
                val imageRef = path.child("${uri.lastPathSegment}")
                val uploadTask = imageRef.putFile(uri)
                uploadTask.addOnFailureListener {
                    //TODO
                }.addOnSuccessListener {
                    //TODO
                }
            }
        }

    }
}