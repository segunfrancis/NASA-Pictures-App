package com.project.segunfrancis.local.source

import com.project.segunfrancis.data.model.NasaItemData
import com.project.segunfrancis.data.source.LocalDataSource
import com.project.segunfrancis.local.mapper.NasaItemLocalMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class LocalDataSourceImpl @Inject constructor(
    private val mapper: NasaItemLocalMapper,
    private val nasaBusinessLogic: NasaBusinessLogic
) : LocalDataSource {

    override fun getData(inputStream: InputStream): Flow<List<NasaItemData>> {
        return flow {
            emit(nasaBusinessLogic.getData(inputStream).map { item ->
                mapper.mapLocalToData(item)
            })
        }
    }
}