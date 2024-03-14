package com.example.tmdbclone.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.service.SearchService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchedMoviesPagingSource @Inject constructor(private val searchService: SearchService) :
    PagingSource<Int, PopularMovieDTO.MovieModelDto>() {

    var query = ""

    override fun getRefreshKey(state: PagingState<Int, PopularMovieDTO.MovieModelDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieDTO.MovieModelDto> {
        return try {

            val nextPage: Int = params.key ?: 1

            val response = searchService.fetchSearchedMovies(query, nextPage)

            if (response.isSuccessful) {

                val data = response.body()!!.results
                val page = response.body()!!.page!!

                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1,
                )
            } else {
                LoadResult.Error(Exception("No Success Response"))
            }

        } catch (e: Exception) {
            Log.d("PagingDataSourceExc", "load: ${e.message}")
            LoadResult.Error(Exception(e))
        }
    }
}