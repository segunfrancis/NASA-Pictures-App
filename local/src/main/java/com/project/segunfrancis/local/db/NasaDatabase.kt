package com.project.segunfrancis.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.segunfrancis.local.model.NasaDao
import com.project.segunfrancis.local.model.NasaItemLocal

/**
 * Created by SegunFrancis
 */

@Database(version = 1, exportSchema = false, entities = [NasaItemLocal::class])
abstract class NasaDatabase : RoomDatabase() {

    abstract fun dao(): NasaDao
}