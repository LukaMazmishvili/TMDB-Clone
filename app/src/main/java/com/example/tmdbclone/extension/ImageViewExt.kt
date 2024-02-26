package com.example.tmdbclone.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.uploadImageByUrl(url: String) {
    Glide.with(this).load(url).into(this)
}