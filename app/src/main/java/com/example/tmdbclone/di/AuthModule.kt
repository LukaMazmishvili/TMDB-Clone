package com.example.tmdbclone.di

import com.example.tmdbclone.domain.AuthValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    // provide dependencies
    @Provides
    @Singleton
    fun provideAuthValidator(): AuthValidator {
        return AuthValidator()
    }
}