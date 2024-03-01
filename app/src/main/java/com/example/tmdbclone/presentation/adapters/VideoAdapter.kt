package com.example.tmdbclone.presentation.adapters

import android.view.View
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.data.remote.model.VideoModelDto
import com.example.tmdbclone.databinding.ItemMovieBinding
import com.example.tmdbclone.extension.uploadImage

class VideoAdapter : BaseAdapter<VideoModelDto.Video, ItemMovieBinding>(ItemMovieBinding::inflate) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        // todo youtube view

        with(holder.binding) {
            ivMovieImage.uploadImage("https://img.youtube.com/vi/${item.key}/mqdefault.jpg")
            tvMovieTitle.visibility = View.GONE
            tvMovieCategories.visibility = View.GONE
        }
    }
}