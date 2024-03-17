package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.adapter

import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.databinding.ItemSeeAllBinding
import com.example.tmdbclone.extension.uploadImage200x300

class TvShowsAdapter :
    BaseAdapter<MoviesDTO.MovieModelDto, ItemSeeAllBinding>(ItemSeeAllBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            with(holder.binding) {
                ivImage.uploadImage200x300(IMAGE_BASE_URL + item.posterPath, false, R.drawable.ic_tv)
                ivImage.minimumHeight = 300
                ivImage.minimumWidth= 210
                tvTitle.text = item.title ?: item.originalName
                tvGenres.text = item.genreIds.toString()
                rvRatings.setOverallRating(item.voteAverage!!)
                rvRatings.fillStars(item.voteAverage)
                rvRatings.setTotalVotes(item.voteCount!!)
                root.setOnClickListener {
                    onItemClickedListener?.invoke(item)
                }
                rvRatings.hideOverallRating()
            }
        }
    }
}