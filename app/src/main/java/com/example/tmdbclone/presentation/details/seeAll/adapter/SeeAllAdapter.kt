package com.example.tmdbclone.presentation.details.seeAll.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbclone.R
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.databinding.ItemSeeAllBinding
import com.example.tmdbclone.extension.uploadImage200x300
import com.example.tmdbclone.utils.GlobalDIffUtil

class SeeAllAdapter :
    PagingDataAdapter<MoviesDTO.MovieModelDto, SeeAllAdapter.ViewHolder>(GlobalDIffUtil<MoviesDTO.MovieModelDto>()) {

    var onItemClickListener: ((MoviesDTO.MovieModelDto) -> Unit)? = null

    class ViewHolder(val binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            with(holder.binding) {
                ivImage.uploadImage200x300(
                    IMAGE_BASE_URL + item.posterPath,
                    false,
                    R.drawable.ic_movies
                )
                ivImage.minimumHeight = 300
                ivImage.minimumWidth = 210
                tvTitle.text = item.title ?: item.originalTitle ?: item.originalName
                tvGenres.text = item.genreIds.toString()
                rvRatings.setOverallRating(item.voteAverage!!)
                rvRatings.fillStars(item.voteAverage)
                rvRatings.setTotalVotes(item.voteCount!!)
                root.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
                rvRatings.hideOverallRating()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSeeAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}