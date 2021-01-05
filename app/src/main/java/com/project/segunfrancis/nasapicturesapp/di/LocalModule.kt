package com.project.segunfrancis.nasapicturesapp.di

import android.content.Context
import androidx.room.Room
import com.project.segunfrancis.local.db.NasaDatabase
import com.project.segunfrancis.nasapicturesapp.util.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NasaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NasaDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}