package com.example.tmdbclone.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tmdbclone.R


fun ImageView.uploadImage80x80(url: String, round: Boolean = false) {
    if (!round) {
        Glide.with(this)
            .load(url)
            .override(140, 140)
            .centerCrop()
            .into(this)
    } else {
        this.setBackgroundResource(R.drawable.bg_stroke_round)
        Glide.with(this)
            .load(url)
            .override(250, 250)
            .circleCrop()
            .into(this)
    }
}

fun ImageView.uploadImage200x300(url: Any, round: Boolean = false) {
    if (!round) {
        Glide.with(this)
            .load(url)
            .override(200, 300)
            .fitCenter()
            .into(this)
    } else {

        // Set Cornered Stroke
        this.setBackgroundResource(R.drawable.bg_stroke_cornered)

        Glide.with(this)
            .load(url)
            .override(250, 350)
            .transform(RoundedCorners(50))
            .into(this)
    }
}

fun ImageView.uploadImage350x450(url: Any, round: Boolean = false) {
    if (!round) {
        Glide.with(this)
            .load(url)
            .override(350, 450)
            .fitCenter()
            .into(this)
    } else {

        // Set Cornered Stroke
        this.setBackgroundResource(R.drawable.bg_stroke_cornered)

        Glide.with(this)
            .load(url)
            .override(250, 350)
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


// todo correct image size
fun ImageView.uploadImage(url: String) {
    Glide.with(this)
        .load(url)
        .override(650, 350)
        .into(this)
}
