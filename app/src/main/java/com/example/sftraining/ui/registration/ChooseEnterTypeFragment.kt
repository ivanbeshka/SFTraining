package com.example.sftraining.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sftraining.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChooseEnterTypeFragment : Fragment() {

    private lateinit var btnWithoutRegistration: MaterialButton
    private lateinit var btnLogIn: MaterialButton
    private lateinit var btnCreateAcc: MaterialButton
    private lateinit var btnSignInWithGoogle: MaterialButton
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.choose_enter_type_fragment, container, false)

        initView(root)

        firebaseAuth = Firebase.auth

        btnCreateAcc.setOnClickListener {
            findNavController().navigate(R.id.navRegisterFragment)
        }

        btnWithoutRegistration.setOnClickListener {
            val dialog = DialogWithoutRegister()
            dialog.show(parentFragmentManager, dialog.tag)
        }

        btnLogIn.setOnClickListener {
            findNavController().navigate(R.id.navLoginFragment)
        }

        btnSignInWithGoogle.setOnClickListener {
            val enterActivity = activity as EnterActivity
            enterActivity.signInWithGoogle()
        }

        return root
    }

    private fun initView(root: View) {
        btnCreateAcc = root.findViewById(R.id.btn_enter_create_acc)
        btnLogIn = root.findViewById(R.id.btn_enter_log_in)
        btnWithoutRegistration = root.findViewById(R.id.btn_continue_without_registration)
        btnSignInWithGoogle = root.findViewById(R.id.btn_sign_in_google)
    }

}