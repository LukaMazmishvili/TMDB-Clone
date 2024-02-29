package com.example.tmdbclone.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tmdbclone.R

class TitledRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

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

    fun setGridRecyclerAdapter(adapter: ListAdapter<*, *>, columns: Int) {
        recyclerView.layoutManager =
            GridLayoutManager(context, columns, GridLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    // todo must rename this function
    fun setRecyclerViewAdapter(adapter: ListAdapter<*, *>) {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        when (adapter.currentList.isNotEmpty()) {
            true -> btnSeeAll.visibility = View.VISIBLE
            false -> btnSeeAll.visibility = View.GONE
        }
    }

}