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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccSettingsFragment : BaseFragment() {

    private lateinit var btnExitAcc: MaterialButton
    private lateinit var tilPersonalInfo: TextInputLayout
    private lateinit var etPersonalInfo: TextInputEditText
    private lateinit var tvPersonalInfo: MaterialTextView

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

        tilPersonalInfo.setEndIconOnClickListener {
            val info = etPersonalInfo.text.toString()
            val lastText = tvPersonalInfo.text
            tvPersonalInfo.text = "$lastText \n $info"
        }

        return root
    }

    override fun initView(root: View) {
        btnExitAcc = root.findViewById(R.id.btn_acc_exit)
        tilPersonalInfo = root.findViewById(R.id.til_personal_info)
        etPersonalInfo = root.findViewById(R.id.et_person_info)
        tvPersonalInfo = root.findViewById(R.id.tv_personal_info)
    }
}