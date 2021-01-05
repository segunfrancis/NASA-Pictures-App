package com.project.segunfrancis.data.mapper

import com.project.segunfrancis.data.model.NasaItemData
import com.project.segunfrancis.domain.model.NasaItemDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class NasaItemMapper @Inject constructor() : Mapper<NasaItemData, NasaItemDomain> {
    override fun mapDataToDomain(data: NasaItemData): NasaItemDomain {
        return with(data) {
            NasaItemDomain(
                copyright,
                date,
                explanation,
                hdurl,
                media_type,
                service_version,
                title,
                url
            )
        }
    }

    override fun mapDomainToData(data: NasaItemDomain): NasaItemData {
        return with(data) {
            NasaItemData(
                copyright,
                date,
                explanation,
                hdurl,
                media_type,
                service_version,
                title,
                url
            )
        }
    }
}