package com.example.tmdbclone.presentation.ui.customViews

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
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.delay

class TitledRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val tvTitle: TextView
    private val btnSeeAll: TextView
    private val recyclerView: RecyclerView
//    private val shimmering: ShimmerFrameLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.cv_titled_recycler_view, this, true)

        tvTitle = findViewById(R.id.tvTitle)
        btnSeeAll = findViewById(R.id.btnSeeAll)
        recyclerView = findViewById(R.id.rvMovies)
//        shimmering = findViewById(R.id.shimmering)


    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun isLoading(isLoading: Boolean) {

//        if (isLoading) {
//            recyclerView.visibility = View.INVISIBLE
//            shimmering.visibility = View.VISIBLE
//        } else {
////            delay(2000)
//            recyclerView.visibility = View.VISIBLE
//            shimmering.visibility = View.INVISIBLE
//        }
    }

    fun setSeeAllButtonClickListener(listener: OnClickListener) {
        btnSeeAll.setOnClickListener(listener)
    }

    fun setGridRecyclerAdapter(adapter: ListAdapter<*, *>, columns: Int) {
        recyclerView.layoutManager =
            GridLayoutManager(context, columns, GridLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    fun setRecyclerViewAdapter(adapter: ListAdapter<*, *>) {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

    }

}