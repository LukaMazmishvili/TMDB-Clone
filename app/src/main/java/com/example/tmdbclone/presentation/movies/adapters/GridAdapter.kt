package com.example.tmdbclone.presentation.movies.adapters

import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.ItemTopRatedBinding
import com.example.tmdbclone.extension.uploadImage150x150

class GridAdapter :
    BaseAdapter<PopularMovieDTO.MovieModelDto, ItemTopRatedBinding>(ItemTopRatedBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {

            ivImage.uploadImage150x150(IMAGE_BASE_URL + item.posterPath)
            tvTitle.text = item.title
            tvGenres.text = item.genreIds.toString()
        }
    }
}