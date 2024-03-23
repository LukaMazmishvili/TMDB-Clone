package com.example.tmdbclone.presentation.adapters

import android.util.Log
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.databinding.ItemTopRatedBinding
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.extension.uploadImage80x80

class GridAdapter :
    BaseAdapter<MovieModel.Movie, ItemTopRatedBinding>(ItemTopRatedBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {

            ivImage.uploadImage80x80(IMAGE_BASE_URL + item.posterPath)
            Log.d("PosterPathInAdapter", "onBindViewHolder: ${IMAGE_BASE_URL + item.posterPath}")
            tvTitle.text = item.title ?: item.originalName
            tvGenres.text = item.genreIds.toString().replace("[", "").replace("]", "")
            root.setOnClickListener {
                onItemClickedListener?.invoke(item)
            }
        }
    }
}