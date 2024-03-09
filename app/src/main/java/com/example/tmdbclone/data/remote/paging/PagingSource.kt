package com.example.tmdbclone.data.remote.paging

import android.util.Log
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.domain.repository.MoviesRepository
import com.example.tmdbclone.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagingSource @Inject constructor(private val moviesService: MoviesService) :
    PagingSource<Int, PopularMovieDTO.MovieModelDto>() {

    override fun getRefreshKey(state: PagingState<Int, PopularMovieDTO.MovieModelDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieDTO.MovieModelDto> {
        return try {

            val nextPage: Int = params.key ?: 1

            val response = moviesService.fetchPopularMovies(nextPage)

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