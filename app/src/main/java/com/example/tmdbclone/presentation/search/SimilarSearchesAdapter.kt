package com.example.tmdbclone.presentation.search

import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.databinding.ItemSimilarSearchBinding
import com.example.tmdbclone.domain.model.SearchSimilarModel

class SimilarSearchesAdapter :
    BaseAdapter<SearchSimilarModel.SimilarSearches, ItemSimilarSearchBinding>(
        ItemSimilarSearchBinding::inflate
    ) {

    var onItemClickListener: ((SearchSimilarModel.SimilarSearches) -> Unit)? = null

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