package com.example.tmdbclone.presentation.adapters

import android.util.Log
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.ItemTopRatedBinding
import com.example.tmdbclone.extension.uploadImage80x80

class GridAdapter :
    BaseAdapter<PopularMovieDTO.MovieModelDto, ItemTopRatedBinding>(ItemTopRatedBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {

            ivImage.uploadImage80x80(IMAGE_BASE_URL + item.posterPath)
            Log.d("PosterPathInAdapter", "onBindViewHolder: ${IMAGE_BASE_URL + item.posterPath}")
            tvTitle.text = item.title ?: item.originalName
            tvGenres.text = item.genreIds.toString()
            root.setOnClickListener {
                onItemClickedListener?.invoke(item)
            }
        }
    }
}