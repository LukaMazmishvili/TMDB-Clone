package com.example.tmdbclone.extension

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tmdbclone.R

fun LinearLayout.insertTextViews(listOfText: List<String>, onClick: View.OnClickListener? = null) {
    for (i in 0..7) {
        val textView = TextView(this.context)
        if (listOfText.isNotEmpty())
            textView.text = listOfText[i]
        textView.setTextColor(this.context.getColor(R.color.app_green))
        textView.textSize = 20f
        textView.setOnClickListener(onClick)
        textView.layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(10, 15, 10, 10)
        }

        textView.gravity = Gravity.CENTER
        this.addView(textView)
    }
}