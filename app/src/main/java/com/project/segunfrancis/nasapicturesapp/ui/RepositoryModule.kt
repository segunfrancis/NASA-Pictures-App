package com.project.segunfrancis.nasapicturesapp.ui

import com.project.segunfrancis.data.repository.LocalRepositoryImpl
import com.project.segunfrancis.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun localRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}