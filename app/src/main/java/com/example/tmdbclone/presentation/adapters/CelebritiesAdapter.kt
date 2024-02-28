package com.example.tmdbclone.presentation.adapters

import android.util.Log
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.databinding.ItemMovieBinding
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.extension.uploadImage750x450

class CelebritiesAdapter :
    BaseAdapter<CelebritiesModelDto.Result, ItemMovieBinding>(ItemMovieBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)

        with(holder.binding) {
            if (item.profilePath != null) {

                ivMovieImage.uploadImage350x450(Endpoints.IMAGE_BASE_URL + item.profilePath, true)
            } else {
                ivMovieImage.uploadImage350x450(R.drawable.placeholder_empty_person)
            }
            ivMovieImage.minimumHeight = 550
            ivMovieImage.minimumWidth = 350
            tvMovieTitle.text = item.name ?: item.originalName
            tvMovieCategories.text = item.knownForDepartment
        }

    }

}