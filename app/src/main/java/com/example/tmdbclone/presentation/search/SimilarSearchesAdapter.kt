package com.example.tmdbclone.presentation.search

import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.databinding.ItemSimilarSearchBinding

class SimilarSearchesAdapter :
    BaseAdapter<SearchSimilarModelDto.SimilarSearches, ItemSimilarSearchBinding>(
        ItemSimilarSearchBinding::inflate
    ) {

    var onItemClickListener: ((SearchSimilarModelDto.SimilarSearches) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            root.text = item.title ?: item.name
            root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}