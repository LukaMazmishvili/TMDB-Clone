package com.example.tmdbclone.presentation.adapters

import android.util.Log
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.databinding.ItemMovieBinding
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.extension.uploadImage750x450

// todo create enum class data type
class PopularAdapter(private val type: Int) :
    BaseAdapter<MovieModel.Movie, ItemMovieBinding>(ItemMovieBinding::inflate) {


    // todo create another view holder for grid recycler item

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            when (type) {
                0 -> {
                    ivMovieImage.uploadImage350x450(IMAGE_BASE_URL + item.posterPath)
                }

                1 -> {
                    ivMovieImage.uploadImage750x450(IMAGE_BASE_URL + item.backdropPath)
                }
            }
            Log.d("Link_In_adapter", "onBindViewHolder: ${IMAGE_BASE_URL + item.posterPath}")
            tvMovieTitle.text = item.title ?: item.originalName
            tvMovieCategories.text = item.genreIds.toString().replace("[", "").replace("]", "")
            root.setOnClickListener {
                onItemClickedListener?.invoke(item)
            }
        }

    }
}