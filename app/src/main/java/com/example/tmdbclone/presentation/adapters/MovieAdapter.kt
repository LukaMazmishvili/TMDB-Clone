package com.example.tmdbclone.presentation.adapters

import android.util.Log
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.databinding.ItemMovieBinding
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.extension.uploadImage750x450

class MovieAdapter(private val type: Int) :
    BaseAdapter<MovieModel.Movie, ItemMovieBinding>(ItemMovieBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            when (type) {
                0 -> {
                    item.posterPath?.let {
                        ivMovieImage.uploadImage350x450(
                            IMAGE_BASE_URL + item.posterPath,
                            placeHolder = R.drawable.ic_movies
                        )
                    }
                }

                1 -> {
                    item.posterPath?.let {
                        ivMovieImage.uploadImage750x450(
                            IMAGE_BASE_URL + item.backdropPath,
                            placeHolder = R.drawable.ic_movies
                        )
                    }
                }
            }
            tvMovieTitle.text = item.title ?: item.originalName
            tvMovieCategories.text = item.genreIds.toString().replace("[", "").replace("]", "")
            root.setOnClickListener {
                onItemClickedListener?.invoke(item)
            }
        }

    }
}