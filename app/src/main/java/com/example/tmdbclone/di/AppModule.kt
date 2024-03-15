package com.example.tmdbclone.di

import android.content.Context
import com.example.tmdbclone.common.Endpoints.BASE_URL
import com.example.tmdbclone.common.Endpoints.OUR_API_BASE_URL
import com.example.tmdbclone.data.remote.paging.PagingSource
import com.example.tmdbclone.data.remote.paging.PagingSourceFactory
import com.example.tmdbclone.data.remote.paging.PagingSourceFactoryImpl
import com.example.tmdbclone.domain.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    @Named("Our Api")
    fun provideRetrofit2(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(OUR_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

    @Provides
    @Singleton
    fun providePagingSourceFactory(pagingSource: PagingSource): PagingSourceFactory {
        return PagingSourceFactoryImpl(pagingSource)
    }
}