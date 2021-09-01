package com.project.segunfrancis.data.source

import com.project.segunfrancis.data.model.NasaItemData
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface LocalDataSource {
    fun getData(): Flow<List<NasaItemData>>

    fun addBookmark(nasaItemData: NasaItemData): Flow<Unit>

    fun getAllBookmarks(): Flow<List<NasaItemData>>

    fun removeBookmark(nasaItemData: NasaItemData): Flow<Unit>
}