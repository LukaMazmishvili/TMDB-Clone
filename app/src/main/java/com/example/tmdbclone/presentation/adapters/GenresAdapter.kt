package com.example.tmdbclone.presentation.adapters

import com.example.tmdbclone.base.BaseAdapter
import com.example.tmdbclone.data.remote.model.GenresModelDto
import com.example.tmdbclone.databinding.ItemGenreBinding

class GenresAdapter : BaseAdapter<String, ItemGenreBinding>(ItemGenreBinding::inflate) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.root.text = item
    }
}