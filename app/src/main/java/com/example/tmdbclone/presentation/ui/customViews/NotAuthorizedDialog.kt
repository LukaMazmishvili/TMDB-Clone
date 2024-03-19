package com.example.tmdbclone.presentation.ui.customViews

import android.app.Dialog
import android.content.Context
import android.widget.Button
import com.example.tmdbclone.R

fun notAuthorizedDialog(context: Context) {

    val dialog = Dialog(context)

    dialog.setContentView(R.layout.diaog_not_authorized)
    dialog.setCancelable(true)

    val okButton = dialog.findViewById<Button>(R.id.ok_button)

    okButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}