package com.example.tmdbclone.presentation.ui.customViews

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import com.example.tmdbclone.R

fun createDialog(context: Context, @StringRes message: Int) {

    val dialog = Dialog(context)

    dialog.setContentView(R.layout.diaog_not_authorized)
    dialog.setCancelable(true)

    val okButton = dialog.findViewById<Button>(R.id.ok_button)
    val textView = dialog.findViewById<TextView>(R.id.textMessage)

    textView.text = context.getString(message)

    okButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}