package com.example.sftraining.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sftraining.R
import com.example.sftraining.ui.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var tilName: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var tilPass: TextInputLayout
    private lateinit var etPass: TextInputEditText
    private lateinit var tilRepeatPass: TextInputLayout
    private lateinit var etRepeatPass: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.register_fragment, container, false)

        firebaseAuth = Firebase.auth

        initView(root)

        btnRegister.setOnClickListener {

            if (EnterActivity.isValidEmail(etEmail.text.toString())) {
                if (etPass.text.toString().isNotEmpty()) {
                    if (etPass.text.toString() == etRepeatPass.text.toString()) {
                        tilPass.error = null
                        tilRepeatPass.error = null
                        tilEmail.error = null

                        registration(etEmail.text.toString(), etPass.text.toString())

                    } else {
                        tilRepeatPass.error = getString(R.string.password_mismatch)
                    }
                }else{
                    tilPass.error = getString(R.string.empty_field)
                }
            } else {
                tilEmail.error = getString(R.string.incorrect_email)
            }

        }

        return root
    }

    private fun registration(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, R.string.register_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView(root: View) {
        tilEmail = root.findViewById(R.id.til_reg_email)
        etEmail = root.findViewById(R.id.et_reg_email)
        tilName = root.findViewById(R.id.til_reg_name)
        etName = root.findViewById(R.id.et_reg_name)
        tilPass = root.findViewById(R.id.til_reg_pass)
        etPass = root.findViewById(R.id.et_reg_pass)
        tilRepeatPass = root.findViewById(R.id.til_reg_repeat_pass)
        etRepeatPass = root.findViewById(R.id.et_reg_repeat_pass)
        btnRegister = root.findViewById(R.id.btn_reg_register)
    }
}