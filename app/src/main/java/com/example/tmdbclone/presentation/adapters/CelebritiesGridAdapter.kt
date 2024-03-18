package com.example.tmdbclone.presentation.adapters

import android.util.Log
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.databinding.ItemTopRatedBinding
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.extension.uploadImage80x80

class CelebritiesGridAdapter :
    BaseAdapter<CelebritiesModel.Result, ItemTopRatedBinding>(ItemTopRatedBinding::inflate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding)
        {

            ivImage.uploadImage80x80(Endpoints.IMAGE_BASE_URL + item.profilePath, true)
            tvTitle.text = item.name ?: item.originalName
            tvGenres.text = item.knownForDepartment
            root.setOnClickListener {
                onItemClickedListener?.invoke(item)
            }
        }
    }
}