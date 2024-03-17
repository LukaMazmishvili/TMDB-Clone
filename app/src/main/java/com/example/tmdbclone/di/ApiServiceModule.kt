package com.example.tmdbclone.di

import com.example.tmdbclone.data.remote.service.UserService
import com.example.tmdbclone.data.remote.service.CelebritiesService
import com.example.tmdbclone.data.remote.service.CelebrityDetailsService
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.MovieDetailService
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.data.remote.service.SearchService
import com.example.tmdbclone.data.remote.service.TvShowsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(@Named("Our Api") retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideMoviesService(@Named("official") retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Provides
    @Singleton
    fun provideTvShowsService(@Named("official") retrofit: Retrofit): TvShowsService =
        retrofit.create(TvShowsService::class.java)

    @Provides
    @Singleton
    fun provideCelebritiesService(@Named("official") retrofit: Retrofit): CelebritiesService =
        retrofit.create(CelebritiesService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailsService(@Named("official") retrofit: Retrofit): MovieDetailService =
        retrofit.create(MovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideGenresService(@Named("official") retrofit: Retrofit): GenresService =
        retrofit.create(GenresService::class.java)

    @Provides
    @Singleton
    fun provideSearchService(@Named("official") retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun provideCelebrityDetailsService(@Named("official") retrofit: Retrofit): CelebrityDetailsService =
        retrofit.create(CelebrityDetailsService::class.java)

}