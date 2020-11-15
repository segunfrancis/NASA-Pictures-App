package com.project.segunfrancis.data.repository

import com.project.segunfrancis.data.mapper.NasaItemMapper
import com.project.segunfrancis.data.source.NasaDataSourceFactory
import com.project.segunfrancis.domain.model.NasaItemDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class LocalRepositoryImpl @Inject constructor(
    private val nasaDataSourceFactory: NasaDataSourceFactory,
    private val nasaItemMapper: NasaItemMapper
) : LocalRepository {

    override fun getData(inputStream: InputStream): Flow<List<NasaItemDomain>> {
        return nasaDataSourceFactory.local().getData(inputStream).map { items ->
            items.map {
                nasaItemMapper.mapDataToDomain(it)
            }
        }
    }
}