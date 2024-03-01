package com.example.tmdbclone.di

import com.example.tmdbclone.data.remote.service.AuthService
import com.example.tmdbclone.data.remote.service.CelebritiesService
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.MovieDetailService
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.data.remote.service.TvShowsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Provides
    @Singleton
    fun provideTvShowsService(retrofit: Retrofit): TvShowsService =
        retrofit.create(TvShowsService::class.java)

    @Provides
    @Singleton
    fun provideCelebritiesService(retrofit: Retrofit): CelebritiesService =
        retrofit.create(CelebritiesService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailsService(retrofit: Retrofit): MovieDetailService =
        retrofit.create(MovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideGenresService(retrofit: Retrofit): GenresService =
        retrofit.create(GenresService::class.java)

}