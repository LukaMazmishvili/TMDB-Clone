package com.example.tmdbclone.di

import android.content.Context
import com.example.tmdbclone.data.remote.repository.UserRepositoryImpl
import com.example.tmdbclone.data.remote.repository.CelebritiesRepositoryImpl
import com.example.tmdbclone.data.remote.repository.CelebrityDetailsRepositoryImpl
import com.example.tmdbclone.data.remote.repository.MovieDetailRepositoryImpl
import com.example.tmdbclone.data.remote.repository.MoviesRepositoryImpl
import com.example.tmdbclone.data.remote.repository.SearchRepositoryImpl
import com.example.tmdbclone.data.remote.repository.TvShowsRepositoryImpl
import com.example.tmdbclone.data.remote.service.UserService
import com.example.tmdbclone.data.remote.service.CelebritiesService
import com.example.tmdbclone.data.remote.service.CelebrityDetailsService
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.IsFavouriteService
import com.example.tmdbclone.data.remote.service.MovieDetailService
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.data.remote.service.SearchService
import com.example.tmdbclone.data.remote.service.TvShowDetailsService
import com.example.tmdbclone.data.remote.service.TvShowsService
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.UserRepository
import com.example.tmdbclone.domain.repository.CelebritiesRepository
import com.example.tmdbclone.domain.repository.CelebrityDetailsRepository
import com.example.tmdbclone.domain.repository.MovieDetailRepository
import com.example.tmdbclone.domain.repository.MoviesRepository
import com.example.tmdbclone.domain.repository.SearchRepository
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
    fun provideMoviesRepo(apiService: MoviesService, genresApi: GenresService): MoviesRepository {
        return MoviesRepositoryImpl(apiService, genresApi)
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
        tvShowDetailsService: TvShowDetailsService,
        isFavouriteService: IsFavouriteService,
        genresApi: GenresService
    ): MovieDetailRepository {
        return MovieDetailRepositoryImpl(
            apiService,
            tvShowDetailsService,
            isFavouriteService,
            genresApi
        )
    }

    @Provides
    @Singleton
    fun provideAuthRepo(
        apiService: UserService,
        sessionManager: SessionManager
    ): UserRepository =
        UserRepositoryImpl(apiService, sessionManager)

    @Provides
    @Singleton
    fun provideSearchRepo(apiService: SearchService): SearchRepository =
        SearchRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideCelebrityDetailsRepo(apiService: CelebrityDetailsService): CelebrityDetailsRepository =
        CelebrityDetailsRepositoryImpl(apiService)

}