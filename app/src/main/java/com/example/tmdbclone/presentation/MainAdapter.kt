package com.example.tmdbclone.presentation

import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.ItemMovieBinding
import com.example.tmdbclone.extension.uploadImageByUrl

class MainAdapter :
    BaseAdapter<PopularMovieDTO.MovieModelDto, ItemMovieBinding>(ItemMovieBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            ivMovieImage.uploadImageByUrl(IMAGE_BASE_URL + item.posterPath)
            tvMovieTitle.text = item.title
            tvMovieCategories.text = item.genreIds.toString()
        }

    }
}