package com.project.segunfrancis.data.repository

import com.project.segunfrancis.data.source.NasaDataSourceFactory
import com.project.segunfrancis.domain.repository.SharedPrefRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(private val dataSourceFactory: NasaDataSourceFactory) :
    SharedPrefRepository {
    override fun isBookmarked(date: String): Flow<Boolean> {
        return dataSourceFactory.local().isBookmarked(date)
    }

    override fun addBookmarkToPref(date: String): Flow<Unit> {
        return dataSourceFactory.local().addBookmarkToSharedPref(date)
    }

    override fun removeBookmarkFromPref(date: String): Flow<Unit> {
        return dataSourceFactory.local().removeBookmarkFromSharedPref(date)
    }
}