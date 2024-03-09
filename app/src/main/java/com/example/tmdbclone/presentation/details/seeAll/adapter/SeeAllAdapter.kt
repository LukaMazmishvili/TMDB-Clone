package com.example.tmdbclone.presentation.details.seeAll.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.ItemSeeAllBinding
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.utils.GlobalDIffUtil

class SeeAllAdapter :
    PagingDataAdapter<PopularMovieDTO.MovieModelDto, SeeAllAdapter.ViewHolder>(GlobalDIffUtil<PopularMovieDTO.MovieModelDto>()) {

    class ViewHolder(private val binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularMovieDTO.MovieModelDto) {
            with(binding) {
                ivImage.uploadImage350x450(IMAGE_BASE_URL + item.posterPath)
                tvTitle.text = item.title
                tvGenres.text = item.genreIds.toString()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSeeAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}