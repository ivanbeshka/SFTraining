package com.example.sftraining.ui.registration

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.sftraining.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var etEmail: TextInputEditText
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var btnSend: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.forgot_pass_fragment, container, false)

        initView(root)

        btnSend.setOnClickListener {
            if (EnterActivity.isValidEmail(etEmail.text.toString())) {
                firebaseAuth.sendPasswordResetEmail(etEmail.text.toString())
                Toast.makeText(context, R.string.msg_sending, Toast.LENGTH_SHORT).show()
            } else {
                textInputLayout.error = getString(R.string.incorrect_email)
            }
        }

        return root
    }

    private fun initView(root: View){
        etEmail = root.findViewById(R.id.et_send_password)
        textInputLayout = root.findViewById(R.id.text_input_layout_send_password)
        btnSend = root.findViewById(R.id.btn_send_pass_link)
    }
}
