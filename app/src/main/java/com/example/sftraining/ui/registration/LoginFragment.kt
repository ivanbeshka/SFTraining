package com.example.sftraining.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sftraining.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var btnForgotPass: MaterialButton
    private lateinit var tilEmail: TextInputLayout
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var btnLogIn: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.login_fragment, container, false)

        initView(root)

        btnForgotPass.setOnClickListener {
            findNavController().navigate(R.id.navForgotPassFragment)
        }

        btnLogIn.setOnClickListener {
            if (EnterActivity.isValidEmail(editTextEmail.text.toString())) {

            } else {
                tilEmail.error = getString(R.string.incorrect_email)
            }
        }



        return root
    }

    private fun initView(root: View) {
        btnForgotPass = root.findViewById(R.id.btn_forgot_pass)
        tilEmail = root.findViewById(R.id.til_login_email)
        editTextEmail = root.findViewById(R.id.et_login_email)
        btnLogIn = root.findViewById(R.id.btn_log_in)
    }
}