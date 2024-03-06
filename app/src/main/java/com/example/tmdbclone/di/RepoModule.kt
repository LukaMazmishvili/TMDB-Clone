package com.example.tmdbclone.di

import android.content.Context
import com.example.tmdbclone.data.remote.repository.UserRepositoryImpl
import com.example.tmdbclone.data.remote.repository.CelebritiesRepositoryImpl
import com.example.tmdbclone.data.remote.repository.MovieDetailRepositoryImpl
import com.example.tmdbclone.data.remote.repository.MoviesRepositoryImpl
import com.example.tmdbclone.data.remote.repository.TvShowsRepositoryImpl
import com.example.tmdbclone.data.remote.service.UserService
import com.example.tmdbclone.data.remote.service.CelebritiesService
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.MovieDetailService
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.data.remote.service.TvShowsService
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.UserRepository
import com.example.tmdbclone.domain.repository.CelebritiesRepository
import com.example.tmdbclone.domain.repository.MovieDetailRepository
import com.example.tmdbclone.domain.repository.MoviesRepository
import com.example.tmdbclone.domain.repository.TvShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideTvShowsRepo(apiService: TvShowsService): TvShowsRepository {
        return TvShowsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCelebritiesRepo(apiService: CelebritiesService): CelebritiesRepository {
        return CelebritiesRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepo(
        apiService: MovieDetailService,
        genresApi: GenresService
    ): MovieDetailRepository {
        return MovieDetailRepositoryImpl(apiService, genresApi)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(
        apiService: UserService,
        sessionManager: SessionManager
    ): UserRepository =
        UserRepositoryImpl(apiService, sessionManager)
}