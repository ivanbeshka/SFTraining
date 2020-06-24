package com.example.sftraining.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sftraining.R
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {

    private lateinit var btnForgotPass: MaterialButton

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

        return root
    }

    private fun initView(root: View) {
        btnForgotPass = root.findViewById(R.id.btn_forgot_pass)
    }
}