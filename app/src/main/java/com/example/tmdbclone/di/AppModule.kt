package com.example.tmdbclone.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.tmdbclone.common.Endpoints.BASE_URL
import com.example.tmdbclone.common.Endpoints.OUR_API_BASE_URL
import com.example.tmdbclone.data.local.SessionManagerImpl
import com.example.tmdbclone.data.remote.paging.PagingSource
import com.example.tmdbclone.data.remote.paging.PagingSourceFactory
import com.example.tmdbclone.data.remote.paging.PagingSourceFactoryImpl
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.network.ConnectivityObserver
import com.example.tmdbclone.network.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("official")
    fun provideRetrofit1(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("Our Api")
    fun provideRetrofit2(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(OUR_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManagerImpl(context)
    }

    @Provides
    @Singleton
    fun providePagingSourceFactory(pagingSource: PagingSource): PagingSourceFactory {
        return PagingSourceFactoryImpl(pagingSource)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityObserver(
        connectivityManager: ConnectivityManager
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(connectivityManager)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}