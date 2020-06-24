package com.example.sftraining.ui.registration

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sftraining.R
import com.example.sftraining.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DialogWithoutRegister : DialogFragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        firebaseAuth = Firebase.auth

        val dialogBuilder =
            AlertDialog.Builder(requireActivity()).setMessage(R.string.dialog_without_register)
                .setPositiveButton(R.string.ok) { _, _ ->

                    dismiss()

                    firebaseAuth.signInAnonymously().addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()

                        } else {

                            Toast.makeText(activity, "Registration fail", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.setNegativeButton(R.string.cancel) { _, _ -> }

        return dialogBuilder.create()
    }
}