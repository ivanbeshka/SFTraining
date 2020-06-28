package com.example.sftraining.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sftraining.R
import com.example.sftraining.ui.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var btnForgotPass: MaterialButton
    private lateinit var tilEmail: TextInputLayout
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var tilPass: TextInputLayout
    private lateinit var etPass: TextInputEditText
    private lateinit var btnLogIn: MaterialButton
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.login_fragment, container, false)

        firebaseAuth = Firebase.auth

        initView(root)

        btnForgotPass.setOnClickListener {
            findNavController().navigate(R.id.navForgotPassFragment)
        }

        btnLogIn.setOnClickListener {
            if (EnterActivity.isValidEmail(editTextEmail.text.toString())) {
                login(editTextEmail.text.toString(), etPass.text.toString())
            } else {
                tilEmail.error = getString(R.string.incorrect_email)
            }
        }
        return root
    }

    private fun login(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, R.string.auth_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView(root: View) {
        btnForgotPass = root.findViewById(R.id.btn_forgot_pass)
        tilEmail = root.findViewById(R.id.til_login_email)
        editTextEmail = root.findViewById(R.id.et_login_email)
        btnLogIn = root.findViewById(R.id.btn_log_in)
        tilPass = root.findViewById(R.id.til_login_password)
        etPass = root.findViewById(R.id.et_login_pass)
    }
}