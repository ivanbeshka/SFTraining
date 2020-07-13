package com.example.sftraining.repository

import android.net.Uri
import com.example.sftraining.model.Exer
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ImageRepository : Repository {
    private val storageReference = Firebase.storage.reference

    companion object {
        const val EXER_IMAGES_PATH = "images"
    }

    fun addExerImages(exer: Exer) {

        val path = storageReference.child(exer.userUid).child(EXER_IMAGES_PATH).child(exer.uid)

        for (imageUri in exer.imageUris) {

            val imageRef = path.child(imageUri.toString())
            val uploadTask = imageRef.putFile(imageUri)
            uploadTask.addOnFailureListener {
                //TODO
            }.addOnSuccessListener {
                //TODO
            }
        }

        val titleImageRef = path.child(exer.titleImageUri.toString())
        val uploadTask = titleImageRef.putFile(exer.titleImageUri)
        uploadTask.addOnSuccessListener {
            //TODO
        }.addOnFailureListener {
            //TODO
        }
    }

    fun getExerImages(exer: Exer): Pair<List<Uri>, Uri> {

        val path = storageReference.child(exer.userUid).child(EXER_IMAGES_PATH).child(exer.uid)

        var titleImageUri: Uri = Uri.EMPTY
        val mainImageUris: MutableList<Uri> = mutableListOf()

        //title image
        path.child(exer.titleImageUri.toString()).downloadUrl
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    titleImageUri = it.result!!
                } else {
                    //TODO
                }
            }
            .addOnFailureListener {
                //TODO
            }

        //main images
        for (mainImageUri in exer.imageUris) {
            path.child(mainImageUri.toString()).downloadUrl
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        mainImageUris.add(it.result!!)
                    } else {
                        //TODO
                    }
                }
                .addOnFailureListener {
                    //TODO
                }
        }

        return Pair(mainImageUris, titleImageUri)
    }
}