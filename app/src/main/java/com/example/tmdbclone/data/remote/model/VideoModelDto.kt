package com.example.tmdbclone.data.remote.model

import com.google.gson.annotations.SerializedName

data class VideoModelDto(
    val id: Int?,
    val results: List<Video>?
) {
    data class Video(
        @SerializedName("iso_639_1")
        val iso6391: String?,
        @SerializedName("iso_3166_1")
        val iso31661: String?,
        val name: String?,
        val key: String?,
        val site: String?,
        val size: Int?,
        val type: String?,
        val official: Boolean?,
        @SerializedName("published_at")
        val publishedAt: String?,
        val id: String?
    )
}