package com.example.tmdbclone.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class GlobalDIffUtil<T>() : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem === newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }
}