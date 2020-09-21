package com.example.sftraining.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sftraining.R
import com.example.sftraining.utils.Email
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        initView(root)

        btnRegister.setOnClickListener {

            //some checks validation
            if (Email.isValid(etEmail.text.toString())) {
                if (etPass.text.toString().isNotEmpty()) {
                    if (etPass.text.toString() == etRepeatPass.text.toString()) {
                        tilPass.error = null
                        tilRepeatPass.error = null
                        tilEmail.error = null

                        val activity = activity as EnterActivity

                        activity.registration(
                            etEmail.text.toString(),
                            etPass.text.toString(),
                            name = etName.text.toString()
                        )

                    } else {
                        tilRepeatPass.error = getString(R.string.password_mismatch)
                    }
                } else {
                    tilPass.error = getString(R.string.empty_field)
                }
            } else {
                tilEmail.error = getString(R.string.incorrect_email)
            }

        }

        return root
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