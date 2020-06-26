package com.example.sftraining.ui.registration

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sftraining.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogWithoutRegister : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder =
            MaterialAlertDialogBuilder(requireActivity()).setMessage(R.string.dialog_without_register)
                .setPositiveButton(R.string.ok) { _, _ ->

                    val activity = activity as EnterActivity
                    activity.anonRegister()

                }.setNegativeButton(R.string.cancel) { _, _ -> }

        return dialogBuilder.create()
    }
}