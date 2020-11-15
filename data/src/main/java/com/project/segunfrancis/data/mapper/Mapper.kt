package com.project.segunfrancis.data.mapper

/**
 * Created by SegunFrancis
 */

interface Mapper<I, O> {
    fun mapDataToDomain(data: I): O
}