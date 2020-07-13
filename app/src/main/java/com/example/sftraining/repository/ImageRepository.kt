package com.example.sftraining.repository

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
            val uploadTask = imageRef.putFile(Uri.parse(imageUri))
            uploadTask.addOnFailureListener {
                //TODO
            }.addOnSuccessListener {
                //TODO
            }
        }

        val titleImageRef = path.child(exer.titleImageUri)
        val uploadTask = titleImageRef.putFile(Uri.parse(exer.titleImageUri))
        uploadTask.addOnSuccessListener {
            //TODO
        }.addOnFailureListener {
            //TODO
        }
    }

    fun getExerImages(
        exer: Exer,
        onTitleImageSuccess: (String) -> Unit = {},
        onMainImagesSuccess: (List<String>) -> Unit = {},
        onFailure: (String?) -> Unit = {}
    ) {

        val path = storageReference.child(exer.userUid).child(EXER_IMAGES_PATH).child(exer.uid)

        //title image
        path.child(exer.titleImageUri.toString()).downloadUrl
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onTitleImageSuccess(it.result.toString())
                } else {
                    onFailure("Task is not successful")
                }
            }
            .addOnFailureListener {
                onFailure(it.message)
            }

        //main images //TODO NEEDS CORUTINE
//        for (mainImageUri in exer.imageUris) {
//            path.child(mainImageUri.toString()).downloadUrl
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        mainImageUris.add(it.result!!)
//                    } else {
//                        //TODO
//                    }
//                }
//                .addOnFailureListener {
//                    //TODO
//                }
//        }
    }
}