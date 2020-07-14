package com.example.sftraining.ui.acc_settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.sftraining.R
import com.example.sftraining.databinding.AccSettingsFragmentBinding
import com.example.sftraining.globalviewmodels.UsersViewModel
import com.example.sftraining.ui.base.BaseFragment
import com.example.sftraining.ui.registration.EnterActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccSettingsFragment : BaseFragment() {

    private lateinit var btnExitAcc: MaterialButton
    private lateinit var tilPersonalInfo: TextInputLayout
    private lateinit var etPersonalInfo: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var tilTag: TextInputLayout
    private lateinit var etTag: TextInputEditText
    private lateinit var tilName: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var btnAddVerify: MaterialButton
    private lateinit var userAvaImage: ShapeableImageView

    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<AccSettingsFragmentBinding>(
            inflater,
            R.layout.acc_settings_fragment,
            container,
            false
        )

        val root = binding.root

        initView(root)

        usersViewModel.getUser().observe(viewLifecycleOwner, Observer {
            binding.user = it
        })

        btnExitAcc.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, EnterActivity::class.java))
            activity?.finish()
        }


        return root
    }

    override fun initView(root: View) {
        btnExitAcc = root.findViewById(R.id.btn_acc_exit_p)
        tilPersonalInfo = root.findViewById(R.id.til_personal_info_p)
        etPersonalInfo = root.findViewById(R.id.et_person_info_p)
        btnAddVerify = root.findViewById(R.id.btn_trainer_verify_p)
        userAvaImage = root.findViewById(R.id.image_acc_avatar_p)
        etTag = root.findViewById(R.id.et_user_tag_p)
        tilEmail = root.findViewById(R.id.til_acc_email_p)
        etEmail = root.findViewById(R.id.et_email_p)
        tilTag = root.findViewById(R.id.til_user_tag_p)
        tilName = root.findViewById(R.id.til_user_name_p)
        etName = root.findViewById(R.id.et_name_p)
    }
}