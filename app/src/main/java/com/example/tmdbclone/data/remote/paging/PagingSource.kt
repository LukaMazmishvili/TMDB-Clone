package com.example.tmdbclone.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbclone.common.Headings
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.data.remote.service.TvShowsService
import javax.inject.Inject

class PagingSource @Inject constructor(
    private val moviesService: MoviesService,
    private val tvShowsService: TvShowsService
) :
    PagingSource<Int, MoviesDTO.MovieModelDto>() {

    var heading: Headings? = null
    var query: String = ""

    override fun getRefreshKey(state: PagingState<Int, MoviesDTO.MovieModelDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDTO.MovieModelDto> {
        return try {

            val nextPage: Int = params.key ?: 1

            val response = when (heading) {
                Headings.Popular -> moviesService.fetchPopularMovies(nextPage)
                Headings.PlayingInTheater -> moviesService.fetchNowPlaying(nextPage.toString())
                Headings.Trending -> moviesService.fetchTrendingMovies(nextPage.toString())
                Headings.TopRated -> moviesService.fetchTopRatedMovies(nextPage.toString())
                Headings.Upcoming -> moviesService.fetchUpcomingMovies(nextPage.toString())
                Headings.TvPopular -> tvShowsService.fetchPopularTvShows(nextPage.toString())
                Headings.TvAiringToday -> tvShowsService.fetchAiringTodayTvShows(nextPage.toString())
                Headings.TvTrending -> tvShowsService.fetchTrendingTvShows(nextPage.toString())
                Headings.TvTopRated -> tvShowsService.fetchTopRatedTvShows(nextPage.toString())

                // if else case fetch movies :d
                else -> moviesService.fetchPopularMovies(nextPage)
            }


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
            LoadResult.Error(Exception(e))
        }
    }

}