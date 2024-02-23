package com.example.tmdbclone.di

import com.example.tmdbclone.data.remote.repository.MoviesRepositoryImpl
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideMoviesRepo(apiService: MoviesService): MoviesRepository {
        return MoviesRepositoryImpl(apiService)
    }
}