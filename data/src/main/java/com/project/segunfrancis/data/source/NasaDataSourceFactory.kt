package com.project.segunfrancis.data.source

import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class NasaDataSourceFactory @Inject constructor(private val localDataSource: LocalDataSource) {
    fun local() = localDataSource
}