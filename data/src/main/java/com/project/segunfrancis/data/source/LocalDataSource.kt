package com.project.segunfrancis.data.source

import com.project.segunfrancis.data.model.NasaItemData
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

/**
 * Created by SegunFrancis
 */
interface LocalDataSource {
    fun getData(inputStream: InputStream): Flow<List<NasaItemData>>
}