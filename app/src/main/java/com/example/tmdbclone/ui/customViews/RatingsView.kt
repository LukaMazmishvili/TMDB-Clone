package com.example.tmdbclone.ui.customViews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.iterator
import com.example.tmdbclone.R

class RatingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val starWrapper: LinearLayout
    private val totalVotes: TextView
    private val overallRating: TextView
    private val overallRatingIc: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.cv_ratings_view, this, true)
        starWrapper = findViewById(R.id.llStarsWrapper)
        totalVotes = findViewById(R.id.tvTotalVotes)
        overallRating = findViewById(R.id.tvOverallRating)
        overallRatingIc = findViewById(R.id.ivOverallRatingIc)
    }

    @SuppressLint("SetTextI18n")
    fun setTotalVotes(totalVotes: Long) {
        this.totalVotes.text = "( $totalVotes) "
    }

    fun setOverallRating(overallRating: Float) {
        this.overallRating.text = overallRating.toString()
    }

    fun hideOverallRating() {
        this.overallRatingIc.visibility = View.GONE
        this.overallRating.visibility = View.GONE
    }

    fun fillStars(overallRating: Float) {
        var lastIndex = 0
        val full = (overallRating / 2).toInt()
        val half = overallRating % 2

        for (i in 0 until full) {
            starWrapper[i].setBackgroundResource(R.drawable.ic_star_filled)
            lastIndex = i
        }

        if (full == 0) {
            starWrapper[0].setBackgroundResource(R.drawable.ic_star_half_filled)
        } else {
            if (lastIndex != 4) {
                starWrapper[lastIndex + 1].setBackgroundResource(R.drawable.ic_star_half_filled)
            }
        }
    }

}