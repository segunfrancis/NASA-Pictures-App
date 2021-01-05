package com.project.segunfrancis.local.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

@Dao
interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(nasaItemLocal: NasaItemLocal)

    @Query("SELECT * FROM nasa_table")
    fun getAllBookmarks(): Flow<List<NasaItemLocal>>

    @Delete
    suspend fun removeBookmark(nasaItemLocal: NasaItemLocal)
}