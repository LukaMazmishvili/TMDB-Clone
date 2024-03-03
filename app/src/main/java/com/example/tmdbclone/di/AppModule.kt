package com.example.tmdbclone.di

import com.example.tmdbclone.common.Endpoints.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("official")
    fun provideRetrofit1(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("Our Api")
    fun provideRetrofit2(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("our base url")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}