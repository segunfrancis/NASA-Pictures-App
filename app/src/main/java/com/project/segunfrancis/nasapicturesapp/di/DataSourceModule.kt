package com.project.segunfrancis.nasapicturesapp.di

import com.project.segunfrancis.data.source.LocalDataSource
import com.project.segunfrancis.data.source.PreferenceHelper
import com.project.segunfrancis.local.source.LocalDataSourceImpl
import com.project.segunfrancis.local.source.PreferenceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindPreferenceHelper(preferenceHelperImpl: PreferenceHelperImpl): PreferenceHelper
}