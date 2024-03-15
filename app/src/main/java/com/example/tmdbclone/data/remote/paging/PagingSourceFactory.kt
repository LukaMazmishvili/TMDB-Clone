package com.example.tmdbclone.data.remote.paging

import com.example.tmdbclone.common.Headings
import javax.inject.Inject
import javax.inject.Singleton

interface PagingSourceFactory {
    fun <T> create(heading: Headings): PagingSource
}

@Singleton
class PagingSourceFactoryImpl @Inject constructor(private val pagingSource: PagingSource) :
    PagingSourceFactory {

    override fun <T> create(heading: Headings): PagingSource {
        pagingSource.heading = heading
        return pagingSource
    }
}