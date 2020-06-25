package com.example.sftraining.ui.registration

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sftraining.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassDialog : DialogFragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var etEmail: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.forgot_pass_fragment, container, false)

        etEmail = root.findViewById(R.id.et_send_password)

        return root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        firebaseAuth = Firebase.auth

        val dialogBuilder =
            AlertDialog.Builder(requireActivity()).setPositiveButton(R.string.send) { _, _ ->
                if (isValidEmail(etEmail.text.toString())) firebaseAuth.sendPasswordResetEmail(
                    etEmail.text.toString()
                )

            }.setTitle(R.string.reset_pass).setView(R.layout.forgot_pass_fragment)

        return dialogBuilder.create()
    }

    private fun isValidEmail(email: String): Boolean {
        val regex = Regex("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
        return email.matches(regex)
    }
}
