package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.SearchRepository
import retrofit2.http.Query
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    suspend fun getSimilarSearches(query: String) = searchRepository.fetchSimilarSearches(query)

    suspend fun getSearchedMovies(query: String, page: Int) =
        searchRepository.fetchSearchedMovies(query, page)

    suspend fun getSearchedTvShows(query: String, page: Int) =
        searchRepository.fetchSearchedTvShows(query, page)

    suspend fun getSearchedCelebrities(query: String, page: Int) =
        searchRepository.fetchSearchedCelebrities(query, page)

}