package com.example.tmdbclone.presentation.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbclone.R

class TitledRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tvTitle: TextView
    private val btnSeeAll: TextView
    private val recyclerView: RecyclerView

    init {
        LayoutInflater.from(context).inflate(R.layout.cv_titled_recycler_view, this, true)

        tvTitle = findViewById(R.id.tvTitle)
        btnSeeAll = findViewById(R.id.btnSeeAll)
        recyclerView = findViewById(R.id.rvMovies)
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun setSeeAllButtonClickListener(listener: OnClickListener) {
        btnSeeAll.setOnClickListener(listener)
    }

    fun setRecyclerViewAdapter(adapter: ListAdapter<*, *>) {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

}