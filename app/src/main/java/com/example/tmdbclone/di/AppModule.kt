package com.example.tmdbclone.di

import com.example.tmdbclone.common.Endpoints.BASE_URL
import com.example.tmdbclone.common.Endpoints.OUR_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // Increase connection timeout to 30 seconds
        .readTimeout(30, TimeUnit.SECONDS) // Increase read timeout to 30 seconds
        .writeTimeout(30, TimeUnit.SECONDS) // Increase write timeout to 30 seconds
        .build()


    @Provides
    @Singleton
    @Named("Our Api")
    fun provideRetrofit2(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(OUR_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

}