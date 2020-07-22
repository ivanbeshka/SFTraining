package com.example.sftraining.di

import com.example.sftraining.repository.ExersRepository
import com.example.sftraining.repository.ImageRepository
import com.example.sftraining.repository.UsersRepository
import com.example.sftraining.repository.TrainingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository() = UsersRepository()

    @Provides
    @Singleton
    fun provideExersRepository() = ExersRepository(ImageRepository())

    @Provides
    @Singleton
    fun provideTrainingsRepository() = TrainingsRepository()
}