package com.example.tmdbclone.domain.usecase

import android.adservices.adid.AdId
import com.example.tmdbclone.domain.repository.CelebrityDetailsRepository
import javax.inject.Inject

class GetCelebrityDetailsUseCase @Inject constructor(private val celebrityDetailsRepository: CelebrityDetailsRepository) {

    suspend fun getCelebrityDetails(personId: Int) =
        celebrityDetailsRepository.fetchCelebrityDetails(personId)

    suspend fun getCelebrityMovieCredits(personId: Int) =
        celebrityDetailsRepository.fetchCelebrityMovieCredits(personId)

    suspend fun getCelebrityTvCredits(personId: Int) =
        celebrityDetailsRepository.fetchCelebrityTvShowCredits(personId)

}