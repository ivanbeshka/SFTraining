package com.example.sftraining.ui.main_menus

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.DialogFragment
import com.example.sftraining.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class SearchFragment : DialogFragment() {

    private lateinit var deleteWritten: AppCompatImageButton
    private lateinit var autoCompleteTextView: MaterialAutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_autocomplete, container, false)

        initView(root)

        deleteWritten.setOnClickListener {
            autoCompleteTextView.text.clear()
        }

        return root
    }

    private fun initView(root: View) {
        deleteWritten = root.findViewById(R.id.btn_delete_written)
        autoCompleteTextView = root.findViewById(R.id.search_autocomplete_tv)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(
            resources.getDrawable(
                R.drawable.dialog_background,
                null
            )
        )

        return dialog
    }

}