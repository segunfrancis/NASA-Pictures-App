package com.project.segunfrancis.data.repository

import com.project.segunfrancis.data.mapper.NasaItemMapper
import com.project.segunfrancis.data.source.NasaDataSourceFactory
import com.project.segunfrancis.domain.model.NasaItemDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class LocalRepositoryImpl @Inject constructor(
    private val nasaDataSourceFactory: NasaDataSourceFactory,
    private val nasaItemMapper: NasaItemMapper
) : LocalRepository {

    override fun getData(): Flow<List<NasaItemDomain>> {
        return nasaDataSourceFactory.local().getData().map { items ->
            items.map { nasaItemMapper.mapDataToDomain(it) }
        }
    }

    override fun addBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit> {
        return nasaDataSourceFactory.local()
            .addBookmark(nasaItemMapper.mapDomainToData(nasaItemDomain))
    }

    override fun getAllBookmarks(): Flow<List<NasaItemDomain>> {
        return nasaDataSourceFactory.local().getAllBookmarks().map { bookmarks ->
            bookmarks.map { nasaItemMapper.mapDataToDomain(it) }
        }
    }

    override fun removeBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit> {
        return nasaDataSourceFactory.local()
            .removeBookmark(nasaItemMapper.mapDomainToData(nasaItemDomain))
    }
}