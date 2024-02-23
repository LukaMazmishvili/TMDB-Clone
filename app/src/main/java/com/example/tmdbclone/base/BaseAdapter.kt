package com.example.tmdbclone.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.tmdbclone.common.inflater
import com.example.tmdbclone.utils.GlobalDIffUtil

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val layout: inflater<VB>
) : ListAdapter<T, BaseAdapter<T, VB>.ViewHolder>(GlobalDIffUtil<T>()) {

    var onItemClickedListener: ((T) -> Unit)? = null

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        layout.invoke(LayoutInflater.from(parent.context), parent, false)
    )
}