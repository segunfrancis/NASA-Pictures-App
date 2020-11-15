package com.project.segunfrancis.nasapicturesapp.mapper

import com.project.segunfrancis.domain.model.NasaItemDomain
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class NasaItemMapper @Inject constructor(): Mapper<NasaItemDomain, NasaItem> {
    override fun mapDomainToAppLayer(data: NasaItemDomain): NasaItem {
        return with(data) {
            NasaItem(copyright, date, explanation, hdurl, media_type, service_version, title, url)
        }
    }
}