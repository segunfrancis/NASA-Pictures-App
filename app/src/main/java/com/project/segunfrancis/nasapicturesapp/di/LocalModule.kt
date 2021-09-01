package com.project.segunfrancis.nasapicturesapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.project.segunfrancis.local.db.NasaDatabase
import com.project.segunfrancis.nasapicturesapp.util.AppConstants.DATABASE_NAME
import com.project.segunfrancis.nasapicturesapp.util.AppConstants.SHARED_PREF_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }
}