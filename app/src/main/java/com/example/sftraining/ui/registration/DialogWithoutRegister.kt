package com.example.sftraining.ui.registration

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sftraining.R
import com.example.sftraining.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DialogWithoutRegister() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder =
            AlertDialog.Builder(requireActivity()).setMessage(R.string.dialog_without_register)
                .setPositiveButton(R.string.ok) { _, _ ->

                    val activity = activity as EnterActivity
                    activity.anonRegister()

                }.setNegativeButton(R.string.cancel) { _, _ -> }

        return dialogBuilder.create()
    }
}