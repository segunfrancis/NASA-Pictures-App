package com.project.segunfrancis.domain.repository

import com.project.segunfrancis.domain.model.NasaItemDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface LocalRepository {
    fun getData(): Flow<List<NasaItemDomain>>
    fun addBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit>
    fun getAllBookmarks(): Flow<List<NasaItemDomain>>
    fun removeBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit>
}