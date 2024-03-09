package com.example.tmdbclone.presentation.details.seeAll.adapter

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.ItemSeeAllBinding
import com.example.tmdbclone.extension.uploadImage200x300
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.extension.uploadImage80x80
import com.example.tmdbclone.utils.GlobalDIffUtil

class SeeAllAdapter :
    PagingDataAdapter<PopularMovieDTO.MovieModelDto, SeeAllAdapter.ViewHolder>(GlobalDIffUtil<PopularMovieDTO.MovieModelDto>()) {

    var onItemClickListener: ((PopularMovieDTO.MovieModelDto) -> Unit)? = null

    class ViewHolder(val binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            with(holder.binding) {
                ivImage.uploadImage200x300(com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL + item.posterPath)
                tvTitle.text = item.title
                tvGenres.text = item.genreIds.toString()
                rvRatings.setOverallRating(item.voteAverage!!)
                rvRatings.fillStars(item.voteAverage)
                rvRatings.setTotalVotes(item.voteCount!!)
                root.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSeeAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}