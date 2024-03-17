package com.example.tmdbclone.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tmdbclone.common.Headings
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.paging.PagingSourceFactory
import com.example.tmdbclone.data.remote.paging.SearchedMoviesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingUseCase @Inject constructor(
    private val pagingSourceFactory: PagingSourceFactory,
    private val searchedMoviesPagingSource: SearchedMoviesPagingSource
) {

    operator fun invoke(heading: Headings): Flow<PagingData<MoviesDTO.MovieModelDto>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                pagingSourceFactory.create<MoviesDTO.MovieModelDto>(
                    heading
                )
            }
        ).flow
    }

    fun getSearchedMovies(query: String): Flow<PagingData<MoviesDTO.MovieModelDto>> {
        searchedMoviesPagingSource.query = query
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { searchedMoviesPagingSource }
        ).flow
    }

}