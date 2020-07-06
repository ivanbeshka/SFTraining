package com.example.sftraining.ui.acc_settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sftraining.R
import com.example.sftraining.ui.base.BaseFragment
import com.example.sftraining.ui.registration.EnterActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccSettingsFragment : BaseFragment() {

    private lateinit var btnExitAcc: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.acc_settings_fragment, container, false)

        initView(root)

        btnExitAcc.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, EnterActivity::class.java))
            activity?.finish()
        }

        return root
    }

    override fun initView(root: View) {
        btnExitAcc = root.findViewById(R.id.btn_acc_exit)
    }
}