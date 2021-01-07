package com.project.segunfrancis.local.source

import com.project.segunfrancis.data.model.NasaItemData
import com.project.segunfrancis.data.source.LocalDataSource
import com.project.segunfrancis.local.db.NasaDatabase
import com.project.segunfrancis.local.db.PreferenceHelper
import com.project.segunfrancis.local.mapper.NasaItemLocalMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class LocalDataSourceImpl @Inject constructor(
    private val mapper: NasaItemLocalMapper,
    private val nasaBusinessLogic: NasaBusinessLogic,
    private val database: NasaDatabase,
    private val preferenceHelper: PreferenceHelper
) : LocalDataSource {

    override fun getData(): Flow<List<NasaItemData>> {
        return flow { emit(nasaBusinessLogic().map { item -> mapper.mapLocalToData(item) }) }
    }

    override fun hasUserSeenOnBoarding(): Flow<Boolean> {
        return flow { emit(preferenceHelper.hasUserSeenOnBoarding()) }
    }

    override fun setUserHasSeenOnBoarding(value: Boolean): Flow<Unit> {
        return flow { emit(preferenceHelper.setUserHasSeenOnBoarding(value)) }
    }

    override fun addBookmark(nasaItemData: NasaItemData): Flow<Unit> {
        return flow { emit(database.dao().addBookmark(mapper.mapDataToLocal(nasaItemData))) }
    }

    override fun getAllBookmarks(): Flow<List<NasaItemData>> {
        return database.dao().getAllBookmarks().map { items ->
            items.map { mapper.mapLocalToData(it) }
        }
    }

    override fun removeBookmark(nasaItemData: NasaItemData): Flow<Unit> {
        return flow {
            emit(database.dao().removeBookmark(mapper.mapDataToLocal(nasaItemData)))
        }
    }

    override fun isBookmarked(date: String): Flow<Boolean> {
        return flow { emit(preferenceHelper.isBookmarked(date)) }
    }

    override fun addBookmarkToSharedPref(date: String): Flow<Unit> {
        return flow { emit(preferenceHelper.addBookmarkToPref(date)) }
    }

    override fun removeBookmarkFromSharedPref(date: String): Flow<Unit> {
        return flow { emit(preferenceHelper.removeBookmarkFromPref(date)) }
    }
}
