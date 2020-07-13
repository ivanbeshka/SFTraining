package com.example.sftraining.ui.app_settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat
import com.example.sftraining.R
import com.example.sftraining.ui.base.BaseFragment
import com.example.sftraining.ui.registration.EnterActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AppSettingsFragment : PreferenceFragmentCompat() {

    private lateinit var btnExitAcc: MaterialButton
    private lateinit var tilPersonalInfo: TextInputLayout
    private lateinit var etPersonalInfo: TextInputEditText
    private lateinit var tvPersonalInfo: MaterialTextView
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_settings_fragment, rootKey);
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.app_settings_fragment, container, false)
//
//        initView(root)
//
//        return root
//    }

//    override fun initView(root: View) {
//        btnExitAcc = root.findViewById(R.id.btn_acc_exit)
//        tilPersonalInfo = root.findViewById(R.id.til_personal_info)
//        etPersonalInfo = root.findViewById(R.id.et_person_info)
//        tvPersonalInfo = root.findViewById(R.id.tv_personal_info)
//    }
}