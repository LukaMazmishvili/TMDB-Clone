package com.example.tmdbclone.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tmdbclone.R


fun ImageView.uploadImage80x80(url: String, round: Boolean = false) {
    this.setBackgroundResource(R.drawable.bg_stroke_round)
    if (!round) {
        Glide.with(this)
            .load(url)
            .override(140, 140)
            .centerCrop()
            .into(this)
    } else {
        Glide.with(this)
            .load(url)
            .override(250, 250)
            .circleCrop()
            .into(this)
    }
}

fun ImageView.uploadImage350x450(url: Any, round: Boolean = false) {
    if (!round) {
        Glide.with(this)
            .load(url)
            .override(350, 450)
            .into(this)
    } else {

        // Set Round Stroke
        this.setBackgroundResource(R.drawable.bg_stroke_cornered)

        Glide.with(this)
            .load(url)
            .override(350, 450)
            .transform(RoundedCorners(50))
            .into(this)
    }
}

fun ImageView.uploadImage750x450(url: String) {
    Glide.with(this)
        .load(url)
        .override(850, 650)
        .into(this)
}
