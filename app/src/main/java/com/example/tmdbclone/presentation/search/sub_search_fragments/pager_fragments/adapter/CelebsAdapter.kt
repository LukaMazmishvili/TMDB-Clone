package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.adapter

import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.databinding.ItemSearchedCelebrityBinding
import com.example.tmdbclone.extension.uploadImage200x300
import com.example.tmdbclone.extension.uploadImage350x450

class CelebsAdapter : BaseAdapter<CelebritiesModelDto.Result, ItemSearchedCelebrityBinding>(
    ItemSearchedCelebrityBinding::inflate
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            with(holder.binding) {
                ivCelebImage.uploadImage200x300(
                    IMAGE_BASE_URL + item.profilePath,
                    false,
                    R.drawable.ic_person
                )
                ivCelebImage.minimumHeight = 300
                ivCelebImage.minimumWidth = 210
                tvCelebName.text = item.name ?: item.originalName
                tvKnowFor.text = item.knownForDepartment
            }
        }
    }
}