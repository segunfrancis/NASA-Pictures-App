package com.project.segunfrancis.local.mapper

import com.project.segunfrancis.data.model.NasaItemData
import com.project.segunfrancis.local.model.NasaItemLocal
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class NasaItemLocalMapper @Inject constructor() : Mapper<NasaItemLocal, NasaItemData> {
    override fun mapLocalToData(data: NasaItemLocal): NasaItemData {
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