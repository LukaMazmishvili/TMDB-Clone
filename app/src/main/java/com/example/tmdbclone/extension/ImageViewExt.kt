package com.example.tmdbclone.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.uploadImage150x150(url: String) {
    Glide.with(this)
        .load(url)
        .override(150, 150)
        .into(this)
}

fun ImageView.uploadImage350x450(url: String) {
    Glide.with(this)
        .load(url)
        .override(350, 450)
        .into(this)
}

fun ImageView.uploadImage750x450(url: String) {
    Glide.with(this)
        .load(url)
        .override(850, 650)
        .into(this)
}
