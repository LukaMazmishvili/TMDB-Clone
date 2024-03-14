package com.example.tmdbclone.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import androidx.paging.cachedIn
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.paging.PagingSource
import com.example.tmdbclone.data.remote.paging.SearchedMoviesPagingSource
import com.example.tmdbclone.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingUseCase @Inject constructor(
    private val pagingSource: PagingSource,
    private val searchedMoviesPagingSource: SearchedMoviesPagingSource
) {

    operator fun invoke(): Flow<PagingData<PopularMovieDTO.MovieModelDto>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    fun getSearchedMovies(query: String): Flow<PagingData<PopularMovieDTO.MovieModelDto>> {
        searchedMoviesPagingSource.query = query
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = Int.MAX_VALUE),
            pagingSourceFactory = { searchedMoviesPagingSource }
        ).flow
    }

}